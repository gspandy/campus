package org.campus.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.campus.core.exception.CampusException;

public class URLBuilder {

    public static String resetUrl(Map<String, String> smsMap, String urlAddress, String charset) {
        if (null == smsMap || smsMap.size() == 0) {
            throw new CampusException("请求参数不能为空");
        }
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        NameValuePair pair;
        final int size = nameValuePairs.size();
        for (Map.Entry<String, String> entry : smsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            pair = new NameValuePair();
            pair.setName(key);
            pair.setValue(value);
            nameValuePairs.add(pair);
        }
        NameValuePair[] pairs = (NameValuePair[]) nameValuePairs.toArray(new NameValuePair[size]);
        String request = EncodingUtil.formUrlEncode(pairs, charset);
        return urlAddress + "?" + request;
    }

}
