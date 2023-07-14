package org.tinycloud.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinycloud.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


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
    public void controllerAPILog() {
    }

    /**
     * 以 provider 包下定义的所有请求为切入点
     */
    @Pointcut("execution(* org.tinycloud..provider..*.*(..))")
    public void providerAPILog() {
    }

    /**
     * 环绕通知
     */
    @Around("controllerAPILog() || providerAPILog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("========================================== Start ==========================================");
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = proceedingJoinPoint.getArgs();

        List<Object> argList = new ArrayList<>();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                    continue;
                }
                if (arg instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) arg;
                    argList.add(file.getOriginalFilename());
                    continue;
                }
                argList.add(arg);
            }
        }
        Object param;
        if (argList.size() == 1) {
            param = argList.get(0);
        } else {
            param = argList;
        }
        // 打印请求 url
        log.info("Request URL    : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("Request IP     : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", JsonUtils.toJsonString(param));


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
