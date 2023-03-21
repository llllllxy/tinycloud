package org.liuxingyu.tinycloud.gateway.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "gateway.config")
public class GatewayConfigProperties {


    /**
     * 白名单，支持通配符模式
     */
    private List<String> skipAuthPath;

    /**
     * 跳过数据权限校验接口path白名单,支持通配符模式
     * 如:下拉数据接口等
     */
    private List<String> skipPermissionPath;

    /**
     * 黑名单
     */
    private List<String> blackIpList;

    /**
     * 登录path
     */
    private String loginPath;

    /**
     * 退出登录path
     */
    private String logoutPath;

    /**
     * jwt加解密密钥
     */
    private String jwtSecret;

    /**
     * 提前续期token时间(分)
     */
    private Integer earlyRenewalTime;

    public List<String> getSkipAuthPath() {
        return skipAuthPath;
    }

    public void setSkipAuthPath(List<String> skipAuthPath) {
        this.skipAuthPath = skipAuthPath;
    }

    public List<String> getSkipPermissionPath() {
        return skipPermissionPath;
    }

    public void setSkipPermissionPath(List<String> skipPermissionPath) {
        this.skipPermissionPath = skipPermissionPath;
    }

    public List<String> getBlackIpList() {
        return blackIpList;
    }

    public void setBlackIpList(List<String> blackIpList) {
        this.blackIpList = blackIpList;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }

    public String getLogoutPath() {
        return logoutPath;
    }

    public void setLogoutPath(String logoutPath) {
        this.logoutPath = logoutPath;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Integer getEarlyRenewalTime() {
        return earlyRenewalTime;
    }

    public void setEarlyRenewalTime(Integer earlyRenewalTime) {
        this.earlyRenewalTime = earlyRenewalTime;
    }
}
