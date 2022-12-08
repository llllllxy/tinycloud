package org.liuxingyu.tinycloud.api;

import org.liuxingyu.tinycloud.api.constant.ServiceNameConstants;
import org.liuxingyu.tinycloud.common.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuxingyu01
 * @date 2022-12-08 13:49
 * @description
 **/
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.USER_SERVICE)
public interface RemoteUserService {

    /**
     * 测试openfeign的调用
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/tinycloud-user/test/testuser")
    public Result getTestUserInfo(@RequestParam("username") String username);

}

