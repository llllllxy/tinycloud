package org.tinycloud.gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 功能:本类功能为将各种对象转化为Json字符串，使用 jackson 包
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class JsonUtils {
    final static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    // ObjectMapper是线程安全的，可以并发的执行它，不会产生任何问题。
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 禁止JsonTool实例化
     */
    private JsonUtils() {

    }

    /**
     * 获取单例（饿汉式单例）
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    /**
     * 将 Object 转化为Json字符串
     *
     * @param 'Object' obj
     * @return String JsonString
     */
    public static String toJsonString(Object obj) {
        String jsonString = "";
        try {
            jsonString = getObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("JsonUtils -- toJsonString -- Exception=", e);
            }
        }
        return jsonString;
    }


    /**
     * 将 Json String 转化为Map
     *
     * @param jsonString 'String JsonString'
     * @return Map returnMap
     */
    public static Map parseMap(String jsonString) {
        if (jsonString != null && !jsonString.trim().isEmpty()) {
            try {
                Map returnMap = getObjectMapper().readValue(jsonString, Map.class);
                return returnMap;
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("JsonUtils -- parseMap -- Exception=", e);
                }
            }
        }
        return null;
    }


    /**
     * JSON字符串转Object
     *
     * @param jsonString 'String JsonString'
     * @param clazz  泛型
     * @param <T> 泛型
     * @return
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (jsonString != null && !jsonString.trim().isEmpty()) {
            try {
                return getObjectMapper().readValue(jsonString, clazz);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("JsonUtils -- parseObject -- Exception=", e);
                }
            }
        }
        return null;
    }


    /**
     * JSON字符串转List
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        if (json != null && !json.trim().isEmpty()) {
            try {
                return getObjectMapper().readValue(json, getObjectMapper().getTypeFactory().constructParametricType(ArrayList.class, clazz));
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("JsonUtils -- parseArray -- Exception=", e);
                }
            }
        }
        return null;
    }

}

