package org.liuxingyu.tinycloud.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.liuxingyu.tinycloud.common.utils.JsonUtils;
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
    @Pointcut("execution(* org.liuxingyu.tinycloud..controller..*.*(..))")
    public void reqOpenAPILog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("reqOpenAPILog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();
        Object param;
        if (args != null && args.length == 1) {
            param = args[0];
        } else {
            param = args;
        }
        // 打印请求 url
        log.info("Request URL    : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("Request IP     : {}", request.getRemoteAddr());
        // 打印请求入参（这是个危险操作，因为如果参数过大的话会导致日志过大）
        log.info("Request Args   : {}", JsonUtils.toJsonString(param));
    }

    /**
     * 在切点之后织入
     */
    @After("reqOpenAPILog()")
    public void doAfter() {
        // TODO:doSth().
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
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", JsonUtils.toJsonString(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        log.info("=========================================== End ===========================================");
        return result;
    }
}
