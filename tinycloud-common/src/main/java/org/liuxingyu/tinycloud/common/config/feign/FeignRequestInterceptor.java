package org.liuxingyu.tinycloud.common.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * <p>
 * 自定义的Feign拦截器
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    /**
     * 这里可以实现对请求的拦截，对请求添加一些额外信息之类的
     *
     * @param requestTemplate RequestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 1. obtain request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 2. 获取不到ServletRequestAttributes的问题（使拦截器直接失效）
        if (Objects.isNull(attributes)) {
            log.error("FeignRequestInterceptor is invalid!");
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 3. obtain request headers，and put it into openFeign RequestTemplate
        Enumeration<String> headerNames = request.getHeaderNames();
        if (Objects.nonNull(headerNames)) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }
    }

}
