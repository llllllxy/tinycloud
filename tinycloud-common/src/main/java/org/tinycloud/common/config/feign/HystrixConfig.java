package org.tinycloud.common.config.feign;

import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name = "feign.hystrix.enabled", havingValue = "true")
public class HystrixConfig {

    @PostConstruct
    public void init() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(
                new RequestHeaderHystrixConcurrencyStrategy()
        );
    }

}
