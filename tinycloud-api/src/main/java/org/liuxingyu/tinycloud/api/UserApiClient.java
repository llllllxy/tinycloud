package org.liuxingyu.tinycloud.api;

import org.liuxingyu.tinycloud.api.constant.ApiServerConstants;
import org.liuxingyu.tinycloud.common.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuxingyu01
 * @date 2022-12-08 13:49
 * @description Feign接口声明
 **/
@FeignClient(value = ApiServerConstants.USER_SERVER, contextId = "remoteUserService", path = "/tinycloud-user")
public interface UserApiClient {

    /**
     * 测试openfeign的调用
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/test/testuser")
    public Result getTestUserInfo(@RequestParam("username") String username);

}

