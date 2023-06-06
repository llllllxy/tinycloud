package org.tinycloud.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.tinycloud.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 统一日志拦截打印切面
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
@Aspect
@Component
public class HttpRequestAspect {
    static final Logger log = LoggerFactory.getLogger(HttpRequestAspect.class);

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(* org.tinycloud..controller..*.*(..))")
    public void reqOpenAPILog() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("reqOpenAPILog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("========================================== Start ==========================================");
        long startTime = System.currentTimeMillis();

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求 url
        log.info("Request URL    : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("Request IP     : {}", request.getRemoteAddr());

        // 打印请求入参（这是个危险操作，因为如果参数过大的话会导致日志过大） -----> 暂时去掉参数的打印
        // Object[] args = proceedingJoinPoint.getArgs();
        // Object param;
        // if (args != null && args.length == 1) {
        //    param = args[0];
        // } else {
        //     param = args;
        // }
        // log.info("Request Args   : {}", JsonUtils.toJsonString(param));

        Object result = proceedingJoinPoint.proceed();
        // 打印执行结果
        log.info("Response Result : {}", JsonUtils.toJsonString(result));
        // 执行耗时
        log.info("Time Consuming  : {} ms", System.currentTimeMillis() - startTime);
        log.info("=========================================== End ===========================================");
        return result;
    }
}
