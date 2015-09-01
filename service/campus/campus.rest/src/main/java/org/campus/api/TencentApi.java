package org.campus.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.campus.api.domain.QqUserinfo;
import org.campus.config.SystemConfig;
import org.campus.core.exception.CampusException;
import org.campus.util.HttpClientUtil;
import org.campus.util.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TencentApi {

    public QqUserinfo getQqUserinfo(String accessToken, String openId) {
        QqUserinfo qqUserinfo = null;
        // Map<String, String> accessTokenMap = getQQAccessToken(accessToken, openId);
        // String openId = getOpenId(accessTokenMap.get("access_token"));
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("oauth_consumer_key", SystemConfig.getString("QQ_CLIENT_ID"));
        map.put("openid", openId);
        try {
            String response = HttpClientUtil.httpGet(getURL(map, SystemConfig.getString("QQ_USERINFO_URL")));
            qqUserinfo = JsonUtil.getInstance().toJavaBean(response, QqUserinfo.class);
            if (qqUserinfo == null || StringUtils.isEmpty(qqUserinfo.getNickname())) {
                throw new CampusException("获取用户信息失败");
            }
            qqUserinfo.setOpenId(openId);
        } catch (Exception e) {
            throw new CampusException("获取用户信息失败", e);
        }
        return qqUserinfo;
    }

    private static String getURL(Map<String, String> smsMap, String urlAddress) {
        if (null == smsMap || smsMap.size() == 0) {
            throw new CampusException("请求参数不能为空");
        }
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        NameValuePair pair;
        final int size = nameValuePairs.size();
        for (Map.Entry<String, String> entry : smsMap.entrySet()) {
            pair = new NameValuePair();
            pair.setName(entry.getKey());
            pair.setValue(entry.getValue());
            nameValuePairs.add(pair);
        }
        NameValuePair[] pairs = (NameValuePair[]) nameValuePairs.toArray(new NameValuePair[size]);
        String request = EncodingUtil.formUrlEncode(pairs, "GBK");
        return urlAddress + "?" + request;
    }

    public static void main(String[] args) {
        TencentApi api = new TencentApi();
        api.getQqUserinfo("DC0AC372C4D56D5B58691D842ADA8CA7", "F6AE3A03FFADD6E56171D380C89F9982");
    }

    // private Map<String, String> getQQAccessToken(String code, String redirectUrl) {
    // Map<String, String> map = new HashMap<String, String>();
    // Map<String, String> respMap = null;
    // try {
    // map.put("grant_type", "authorization_code");
    // map.put("client_id", SystemConfig.getString("QQ_CLIENT_ID"));
    // map.put("client_secret", SystemConfig.getString("QQ_CLIENT_SECRET"));
    // map.put("code", code);
    // map.put("client_secret", redirectUrl);
    // String response = HttpClientUtil.get(getURL(map, SystemConfig.getString("QQ_TOKEN_URL")));
    // respMap = parseResponse(response);
    // if (CollectionUtils.isEmpty(respMap) || StringUtils.isEmpty(respMap.get("access_token"))) {
    // throw new CampusException("获取access_token失败");
    // }
    // } catch (Exception e) {
    // throw new CampusException("获取access_token失败");
    // }
    // return respMap;
    // }
    //
    // private String getOpenId(String accessToken) {
    // Map<String, String> respMap = null;
    // Map<String, String> map = new HashMap<String, String>();
    // map.put("access_token", accessToken);
    // String response = HttpClientUtil.get(getURL(map, SystemConfig.getString("QQ_OPENID_URL")));
    // respMap = parseResponse(response);
    // if (CollectionUtils.isEmpty(respMap) || StringUtils.isEmpty(respMap.get("openid"))) {
    // throw new CampusException("获取openid失败");
    // }
    // return respMap.get("openid");
    // }

    // private Map<String, String> parseResponse(String response) {
    // Map<String, String> respMap = new HashMap<String, String>();
    // String[] responses = response.split("&");
    // for (int i = 0; i < responses.length; i++) {
    // String fields = responses[i];
    // int index = fields.indexOf("=");
    // if (index < 0) {
    // continue;
    // }
    // respMap.put(fields.substring(0, index), fields.substring(index + 1));
    // }
    // return respMap;
    // }

}
