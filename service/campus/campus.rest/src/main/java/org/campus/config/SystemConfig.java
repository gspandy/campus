package org.campus.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public abstract class SystemConfig {

    private static final Logger logger = LoggerFactory.getLogger(SystemConfig.class);

    private static Configuration configuration;

    private static final String ENCRYPT = "encrypt";

    static {
        init();
    }

    private static void init() {
        Properties props = null;
        try {
            props = ConfigurationLoader.getConfigProperties();
        } catch (IOException e) {
            logger.error("初始化系统配置发生IO异常", e);
            return;
        }

        configuration = new PropertiesConfiguration(props);
    }

    public static Configuration subset(String prefix) {
        return configuration.subset(prefix);
    }

    public static boolean containsKey(String key) {
        return configuration.containsKey(key);
    }

    public static Object getProperty(String key) {
        return configuration.getProperty(key);
    }

    public static Set<String> getKeys(String prefix) {
        return configuration.getKeys(prefix);
    }

    public static Set<String> getKeys() {
        return configuration.getKeys();
    }

    public static Properties getProperties(String key) {
        return configuration.getProperties(key);
    }

    public static boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return configuration.getBoolean(key, defaultValue);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return configuration.getBoolean(key, defaultValue);
    }

    public static byte getByte(String key) {
        return configuration.getByte(key);
    }

    public static byte getByte(String key, byte defaultValue) {
        return configuration.getByte(key, defaultValue);
    }

    public static Byte getByte(String key, Byte defaultValue) {
        return configuration.getByte(key, defaultValue);
    }

    public static double getDouble(String key) {
        return configuration.getDouble(key);
    }

    public static double getDouble(String key, double defaultValue) {
        return configuration.getDouble(key, defaultValue);
    }

    public static Double getDouble(String key, Double defaultValue) {
        return configuration.getDouble(key, defaultValue);
    }

    public static float getFloat(String key) {
        return configuration.getFloat(key);
    }

    public static float getFloat(String key, float defaultValue) {
        return configuration.getFloat(key, defaultValue);
    }

    public static Float getFloat(String key, Float defaultValue) {
        return configuration.getFloat(key, defaultValue);
    }

    public static int getInt(String key) {
        return configuration.getInt(key);
    }

    public static int getInt(String key, int defaultValue) {
        return configuration.getInt(key, defaultValue);
    }

    public static Integer getInteger(String key, Integer defaultValue) {
        return configuration.getInteger(key, defaultValue);
    }

    public static long getLong(String key) {
        return configuration.getLong(key);
    }

    public static long getLong(String key, long defaultValue) {
        return configuration.getLong(key, defaultValue);
    }

    public static Long getLong(String key, Long defaultValue) {
        return configuration.getLong(key, defaultValue);
    }

    public static short getShort(String key) {
        return configuration.getShort(key);
    }

    public static short getShort(String key, short defaultValue) {
        return configuration.getShort(key, defaultValue);
    }

    public static Short getShort(String key, Short defaultValue) {
        return configuration.getShort(key, defaultValue);
    }

    public static BigDecimal getBigDecimal(String key) {
        return configuration.getBigDecimal(key);
    }

    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        return configuration.getBigDecimal(key, defaultValue);
    }

    public static BigInteger getBigInteger(String key) {
        return configuration.getBigInteger(key);
    }

    public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
        return configuration.getBigInteger(key, defaultValue);
    }

    public static String getString(String key) {
        return configuration.getString(key);
    }

    public static String getEncryptString(String key) {
        return configuration.getString(key + "." + ENCRYPT);
    }

    public static String getString(String key, String defaultValue) {
        return configuration.getString(key, defaultValue);
    }

    public static String[] getStringArray(String key) {
        return configuration.getStringArray(key);
    }

    public static List<Object> getList(String key) {
        return configuration.getList(key);
    }

    public static List<Object> getList(String key, List<?> defaultValue) {
        return configuration.getList(key, defaultValue);
    }

    public static Resource getConfigFile(String fileName) {
        return ConfigurationLoader.getConfigFile(fileName);
    }

}