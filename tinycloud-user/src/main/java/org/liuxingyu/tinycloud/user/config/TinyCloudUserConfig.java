package org.liuxingyu.tinycloud.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuxingyu01
 * @date 2022-12-08 11:44
 * @description TinyCloudUser配置类，支持nacos动态刷新
 **/
@Component
@ConfigurationProperties(prefix = "tinycloud")
public class TinyCloudUserConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目版本
     */
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
