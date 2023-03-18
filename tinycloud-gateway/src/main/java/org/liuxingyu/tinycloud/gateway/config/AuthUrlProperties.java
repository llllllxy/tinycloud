package org.liuxingyu.tinycloud.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="system")
public class AuthUrlProperties {
    /**
     * 放行API集合
     */
    private List<String> skipUrl;

    public List<String> getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(List<String> skipUrl) {
        this.skipUrl = skipUrl;
    }
}
