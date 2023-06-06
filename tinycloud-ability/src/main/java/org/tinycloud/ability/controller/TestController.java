package org.tinycloud.ability.controller;

import org.tinycloud.common.model.ApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxingyu01
 * @date 2022-12-08 13:19
 * @description
 **/
@RefreshScope
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${tinycloud.name}")
    private String name;


    @RequestMapping("/testability")
    public ApiResult<?> testability() {
        return ApiResult.success("调用成功", name);
    }

}
