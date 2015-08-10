package org.campus.util;

import java.security.MessageDigest;

import org.campus.config.SystemConfig;
import org.campus.core.exception.CampusException;

public class MD5Util {

    /**
     * 
     * 功能描述: <br>
     * MD5加密
     *
     * @param origin 待加密数据
     * @return
     *
     */
    public static String encrypt(String origin) {
        return encrypt(origin, SystemConfig.getString("password_md5_charset"));
    }

    /**
     * 
     * 功能描述: <br>
     * MD5加密
     *
     * @param origin 待加密数据
     * @param charSet 字符集
     * @return
     *
     */
    public static String encrypt(String origin, String charSet) {
        if (origin != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(origin).append(SystemConfig.getString("password_md5_key"));
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(buffer.toString().getBytes(charSet));
                return byteArrayToHexString(results).toUpperCase();
            } catch (Exception ex) {
                throw new CampusException("MD5加密失败");
            }
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hex = Integer.toHexString(v);
            if (hex.length() < 2) {
                buffer.append(0);
            }
            buffer.append(hex);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin"));
    }

}
