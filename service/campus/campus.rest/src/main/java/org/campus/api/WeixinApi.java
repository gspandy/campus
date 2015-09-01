package org.campus.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.lang.StringUtils;
import org.campus.api.domain.SnsapiUserinfo;
import org.campus.config.SystemConfig;
import org.campus.core.exception.CampusException;
import org.campus.util.HttpClientUtil;
import org.campus.util.JsonUtil;
import org.springframework.stereotype.Component;

@Component
public class WeixinApi {

    public SnsapiUserinfo getSnsapiUserinfo(String accessToken, String openId) {
        // WebAccessToken webAccessToken = getWebAccessToken(accessToken,String openId);
        SnsapiUserinfo snsapiUserinfo = null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("openid", openId);
        map.put("lang", "zh_CN");
        String response = HttpClientUtil.httpGet(getURL(map, SystemConfig.getString("WEIXIN_USERINFO_URL")));
        // String response =
        // "{\"openid\":\"OPENID\",\"nickname\":\"NICKNAME\",\"sex\":\"1\",\"province\":\"PROVINCE\",\"city\":\"CITY\",\"country\":\"COUNTRY\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46\",\"privilege\":[\"12\",\"3\"],\"unionid\":\"o6_bmasdasdsad6_2sgVt7hMZOPfL\"}";
        try {
            snsapiUserinfo = JsonUtil.getInstance().toJavaBean(response, SnsapiUserinfo.class);
            if (snsapiUserinfo == null || StringUtils.isEmpty(snsapiUserinfo.getOpenid())) {
                throw new CampusException("获取用户信息失败");
            }
        } catch (Exception e) {
            throw new CampusException("获取用户信息失败", e);
        }
        return snsapiUserinfo;
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
        String request = EncodingUtil.formUrlEncode(pairs, "UTF-8");
        return urlAddress + "?" + request;
    }

    public static void main(String[] args) {
        WeixinApi api = new WeixinApi();
        api.getSnsapiUserinfo(
                "OezXcEiiBSKSxW0eoylIePqq3Qfw8J-SmYJO_OvsvrcUtt0uAfGz7fyiUsApjwlGdSQBmGOqe80RjMoGWMO7-5mR3F8woVgRu-Zgscei5ICtBUUm2mdKCjPN6sIDGGYbCl0_GTU5W_wo8X37p-HACA",
                "ovT2lwKk9e2ytT6O4tUCR0HGBVGo");
    }

    // private WebAccessToken getWebAccessToken(String code) {
    // WebAccessToken accessToken = null;
    // Map<String, String> map = new HashMap<String, String>();
    // try {
    // map.put("appid", SystemConfig.getString("WEIXIN_APPID"));
    // map.put("secret", SystemConfig.getString("WEIXIN_SECRET"));
    // map.put("code", code);
    // map.put("grant_type", "authorization_code");
    // String response = HttpClientUtil.get(getURL(map, SystemConfig.getString("WEIXIN_TOKEN_URL")));
    // // String response =
    // //
    // "{\"access_token\":\"OezXcEiiBSKSxW0eoylIeER7_sKq7Sx0WTHC7OAeLZ6lHYlew_kdo6lorrOWV-axvZm5BNJyvGLPaKiyfijLN9Vl-5eC1L5oYReNqERhzLJvSucRJwU448ao1u48IoiXeLZpjUmh3DAfzGC84q0QLg\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeER7_sKq7Sx0WTHC7OAeLZ6lHYlew_kdo6lorrOWV-axgGIqlMjziV6MpwGwowY8dvRMOTnwo0fUVKlTqRkq3rXTuICrnJudscYXqhwG-TZd-2mRuwaANpjpqNBY4iSPBw\",\"openid\":\"ot6Sit9XxNopf832fOVn1wq7fhG4\",\"scope\":\"snsapi_userinfo\",\"unionid\":\"obShouAI4nzDrA4GkvjhnIUMtpns\"}";
    // accessToken = JsonUtil.getInstance().toJavaBean(response, WebAccessToken.class);
    // if (accessToken == null || StringUtils.isEmpty(accessToken.getAccess_token())) {
    // throw new CampusException("获取access_token失败");
    // }
    // } catch (Exception e) {
    // throw new CampusException("获取access_token失败");
    // }
    // return accessToken;
    // }

}
