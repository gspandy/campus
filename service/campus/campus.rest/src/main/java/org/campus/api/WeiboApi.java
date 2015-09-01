package org.campus.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.campus.api.domain.WeiboUserInfo;
import org.campus.config.SystemConfig;
import org.campus.core.exception.CampusException;
import org.campus.util.HttpClientUtil;
import org.campus.util.JsonUtil;
import org.springframework.stereotype.Component;

@Component
public class WeiboApi {

    public WeiboUserInfo getWeiboUserinfo(String accessToken, String openId) {
        WeiboUserInfo weiboUserInfo = null;
        // WeiboAccessToken accessToken = getWeiboAccessToken(accessToken, openId);
        // WeiboUid uid = getUid(accessToken.getAccess_token());
        Map<String, String> map = new HashMap<String, String>();
        map.put("source", SystemConfig.getString("WEIBO_CLIENT_ID"));
        map.put("access_token", accessToken);
        map.put("uid", openId);
        try {
            String response = HttpClientUtil.httpGet(getURL(map, SystemConfig.getString("WEIBO_USERINFO_URL")));
            weiboUserInfo = JsonUtil.getInstance().toJavaBean(response, WeiboUserInfo.class);
            if (weiboUserInfo == null || weiboUserInfo.getId() == 0) {
                throw new CampusException("获取用户信息失败");
            }
        } catch (Exception e) {
            throw new CampusException("获取用户信息失败", e);
        }
        return weiboUserInfo;
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

    // private WeiboAccessToken getWeiboAccessToken(String code, String redirectUrl) {
    // Map<String, String> map = new HashMap<String, String>();
    // WeiboAccessToken accessToken = null;
    // try {
    // map.put("client_id", SystemConfig.getString("WEIBO_CLIENT_ID"));
    // map.put("client_secret", SystemConfig.getString("WEIBO_CLIENT_SECRET"));
    // map.put("grant_type", "authorization_code");
    // map.put("redirect_uri", redirectUrl);
    // map.put("code", code);
    // String response = HttpClientUtil.get(getURL(map, SystemConfig.getString("WEIBO_TOKEN_URL")));
    // accessToken = JsonUtil.getInstance().toJavaBean(response, WeiboAccessToken.class);
    // if (accessToken == null || StringUtils.isEmpty(accessToken.getAccess_token())) {
    // throw new CampusException("获取access_token失败");
    // }
    // } catch (Exception e) {
    // throw new CampusException("获取access_token失败");
    // }
    // return accessToken;
    // }
    //
    // private WeiboUid getUid(String accessToken) {
    // WeiboUid weiboUid = null;
    // Map<String, String> map = new HashMap<String, String>();
    // map.put("source", SystemConfig.getString("WEIBO_CLIENT_ID"));
    // map.put("access_token", accessToken);
    // String response = HttpClientUtil.get(getURL(map, SystemConfig.getString("WEIBO_UID_URL")));
    // weiboUid = JsonUtil.getInstance().toJavaBean(response, WeiboUid.class);
    // if (weiboUid == null) {
    // throw new CampusException("获取微博Uid失败");
    // }
    // return weiboUid;
    // }

}
