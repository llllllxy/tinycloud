package org.tinycloud.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.tinycloud.common.config.redis.RedisClient;
import org.tinycloud.common.model.ApiResult;
import org.tinycloud.user.config.TinyCloudUserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "用户中心-测试", value = "用户中心-测试")
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TinyCloudUserConfig tinyCloudUserConfig;

    @Autowired
    private RedisClient redisClient;


    @ApiOperation(value = "测试配置动态刷新", notes = "测试配置动态刷新")
    @RequestMapping(value = "/testConfig", method = RequestMethod.GET)
    public ApiResult<?> testConfig() {
        return ApiResult.success(tinyCloudUserConfig.getName(), "调用成功");
    }


    @ApiOperation(value = "测试Redis", notes = "测试Redis")
    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public ApiResult<?> testRedis() {
        redisClient.set("tinycloud", "testdata", 100);
        return ApiResult.success(null, "调用成功");
    }
}
