package org.tinycloud.common.utils.web;

import org.tinycloud.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liuxingyu01
 * @date 2021-01-30-11:21
 **/
public class ResponseUtils {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static void sendJson(ServletResponse response, Object responseObject) {
        // 将实体对象转换为JSON Object转换
        String jsonStr = JsonUtils.toJsonString(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.append(jsonStr);
        } catch (IOException e) {
            logger.error("ResponseUtil -- sendJson -- IOException = {e}", e);
        }
    }
}
