package org.campus.util;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class ToolUtil {

    private static SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);

    private static Random random = new Random(100000);

    /**
     * ID生成器
     * 
     * @return
     */
    public static synchronized long getId() {
        return idGenerator.nextId();
    }

    /**
     * 获取UUID
     * 
     * @return
     */
    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 生成6位随机数
     * 
     * @return
     */
    public static String getSmsCheckColde() {
        int code = random.nextInt(999999);
        return Integer.toString(code);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

}
