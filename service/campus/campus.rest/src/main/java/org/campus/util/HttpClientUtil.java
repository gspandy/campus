package org.campus.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.campus.config.SystemConfig;
import org.springframework.util.StringUtils;

public class HttpClientUtil {

    private static final String SSL_DEFAULT_SCHEME = "https";

    private static final int SSL_DEFAULT_PORT = 443;

    @SuppressWarnings("deprecation")
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= 2) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
            if (!idempotent) {
                return true;
            }
            return false;
        }
    };

    @SuppressWarnings("deprecation")
    private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String charset = EntityUtils.getContentCharSet(entity) == null ? SystemConfig
                        .getString("SMS_REQUEST_CHARSET") : EntityUtils.getContentCharSet(entity);
                return new String(EntityUtils.toByteArray(entity), charset);
            } else {
                return null;
            }
        }
    };

    public static String get(String url, String charset) {
        return get(url, null, charset);
    }

    @SuppressWarnings("deprecation")
    public static String get(String url, Map<String, String> params, String charset) {
        if (url == null || StringUtils.isEmpty(url)) {
            return null;
        }
        List<NameValuePair> qparams = getParamsList(params);
        if (qparams != null && qparams.size() > 0) {
            charset = (charset == null ? SystemConfig.getString("DEFAULT_CHARSET") : charset);
            String formatParams = URLEncodedUtils.format(qparams, charset);
            url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)
                    : (url.substring(0, url.indexOf("?") + 1) + formatParams);
        }
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        HttpGet hg = new HttpGet(url);
        String responseStr = null;
        try {
            responseStr = httpclient.execute(hg, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (hg != null) {
                hg.abort();
            }
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return responseStr;
    }

    @SuppressWarnings("deprecation")
    public static String get(String url, Map<String, String> params, String requestCharset, final String responseCharset) {
        if (url == null || StringUtils.isEmpty(url)) {
            return null;
        }
        List<NameValuePair> qparams = getParamsList(params);
        if (qparams != null && qparams.size() > 0) {
            requestCharset = (requestCharset == null ? SystemConfig.getString("DEFAULT_CHARSET") : requestCharset);
            String formatParams = URLEncodedUtils.format(qparams, requestCharset);
            url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)
                    : (url.substring(0, url.indexOf("?") + 1) + formatParams);
        }
        DefaultHttpClient httpclient = getDefaultHttpClient(requestCharset);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
        HttpGet hg = new HttpGet(url);
        String responseStr = null;
        try {
            responseStr = httpclient.execute(hg, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        return null;
                    }

                    String charset = EntityUtils.getContentCharSet(entity) == null ? responseCharset : EntityUtils
                            .getContentCharSet(entity);

                    return new String(EntityUtils.toByteArray(entity), charset);
                }
            });
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (hg != null) {
                hg.abort();
            }
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return responseStr;
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return post(url, params, null);
    }

    @SuppressWarnings("deprecation")
    public static String post(String url, Map<String, String> params, String charset) throws Exception {
        String responseStr = null;
        HttpPost hp = new HttpPost(url);
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
        try {
            if (url == null || StringUtils.isEmpty(url)) {
                return null;
            }
            UrlEncodedFormEntity formEntity = null;
            if (params != null && params.size() != 0) {
                if (charset == null || StringUtils.isEmpty(charset)) {
                    formEntity = new UrlEncodedFormEntity(getParamsList(params));
                } else {
                    formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
                }
                hp.setEntity(formEntity);
            }
            responseStr = httpclient.execute(hp, responseHandler);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (hp != null) {
                hp.abort();
            }
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return responseStr;
    }

    @SuppressWarnings("deprecation")
    public static String post(String url, Map<String, String> params, String charset, final URL keystoreUrl,
            final String keystorePassword, final URL truststoreUrl, final String truststorePassword) {
        if (url == null || StringUtils.isEmpty(url)) {
            return null;
        }
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
        UrlEncodedFormEntity formEntity = null;
        try {
            if (charset == null || StringUtils.isEmpty(charset)) {
                formEntity = new UrlEncodedFormEntity(getParamsList(params));
            } else {
                formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpPost hp = null;
        String responseStr = null;
        try {
            KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
            KeyStore trustStore = createKeyStore(truststoreUrl, keystorePassword);
            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, keystorePassword, trustStore);
            Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, SSL_DEFAULT_PORT);
            httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
            hp = new HttpPost(url);
            hp.setEntity(formEntity);
            responseStr = httpclient.execute(hp, responseHandler);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (hp != null) {
                hp.abort();
            }
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return responseStr;
    }

    public static String post(String url, String xmlString, String charset) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(url);
        client.getParams().setSoTimeout(500 * 1000);
        String responseString = null;
        try {
            myPost.setRequestEntity(new StringRequestEntity(xmlString, "text/xml", charset));
            int statusCode = client.executeMethod(myPost);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                responseString = new String(strByte, 0, strByte.length, charset);
                bos.close();
                bis.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            myPost.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return responseString;
    }

    @SuppressWarnings("deprecation")
    private static DefaultHttpClient getDefaultHttpClient(final String charset) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        DefaultHttpClient httpclients = null;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpclient.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            httpclients = new DefaultHttpClient(ccm, httpclient.getParams());
            httpclients.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            httpclients.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            httpclients.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
            httpclients.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
                    charset == null ? SystemConfig.getString("DEFAULT_CHARSET") : charset);
            httpclients.setHttpRequestRetryHandler(requestRetryHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpclients;
    }

    private static KeyStore createKeyStore(final URL url, final String password) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        if (url == null) {
            throw new IllegalArgumentException("Keystore url may not be null");
        }
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = null;
        try {
            is = url.openStream();
            keystore.load(is, password != null ? password.toCharArray() : null);
        } finally {
            if (is != null) {
                is.close();
                is = null;
            }
        }
        return keystore;
    }

    private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return null;
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }
        return params;
    }

}