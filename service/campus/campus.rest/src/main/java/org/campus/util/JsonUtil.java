package org.campus.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static Logger logger = Logger.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static JsonUtil jsonUtil = null;

    private JsonUtil() {
    }

    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }

    public String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("转换为json字符串失败" + e.toString());
        } catch (JsonMappingException e) {
            logger.error("转换为json字符串失败" + e.toString());
        } catch (IOException e) {
            logger.error("转换为json字符串失败" + e.toString());
        }
        return null;
    }

    public <T> T toJavaBean(String content, Class<T> valueType) {
        // 忽略json中有，而javabean中没有的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonParseException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        } catch (JsonMappingException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        } catch (IOException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        }
        return null;
    }
}
