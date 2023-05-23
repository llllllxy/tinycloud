package org.liuxingyu.tinycloud.api.factory;

import feign.hystrix.FallbackFactory;
import org.liuxingyu.tinycloud.api.UserApiClient;
import org.liuxingyu.tinycloud.bean.vo.UcUserVo;
import org.liuxingyu.tinycloud.common.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author liuxingyu01
 */
@Component
public class UserApiFallbackFactory implements FallbackFactory<UserApiClient> {
    private static final Logger log = LoggerFactory.getLogger(UserApiFallbackFactory.class);

    @Override
    public UserApiClient create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new UserApiClient() {

            @Override
            public ApiResult<UcUserVo> detail(String username) {
                return ApiResult.fail("获取用户信息失败: " + throwable.getMessage());
            }

        };
    }
}
