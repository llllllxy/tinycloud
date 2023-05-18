package org.liuxingyu.tinycloud.common.config.feign;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Callable;


@Component
public class RequestHeaderHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    public static final Logger logger = LoggerFactory.getLogger(RequestHeaderHystrixConcurrencyStrategy.class);

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable){
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                try {
                    if (null != servletRequestAttributes) {
                        logger.info("子线程继承父线程请求");
                        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
                    }
                    return callable.call();
                }finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            }
        };
    }

}
