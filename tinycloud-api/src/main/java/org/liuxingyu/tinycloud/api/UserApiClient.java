package org.liuxingyu.tinycloud.api;

import org.liuxingyu.tinycloud.api.constant.ApiServerConstants;
import org.liuxingyu.tinycloud.api.factory.UserApiFallbackFactory;
import org.liuxingyu.tinycloud.bean.entity.UcUser;
import org.liuxingyu.tinycloud.common.model.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liuxingyu01
 * @date 2022-12-08 13:49
 * @description 用户服务Feign接口声明
 **/
@FeignClient(value = ApiServerConstants.USER_SERVER, contextId = "remoteUserService", path = "/user-server", fallbackFactory = UserApiFallbackFactory.class)
public interface UserApiClient {

    @GetMapping(value = "/api/user/detail/{userId}")
    ApiResult<UcUser> detail(@PathVariable("userId") String userId);
}

