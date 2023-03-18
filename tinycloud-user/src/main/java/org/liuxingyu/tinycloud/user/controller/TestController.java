package org.liuxingyu.tinycloud.user.controller;

import org.liuxingyu.tinycloud.common.base.Result;
import org.liuxingyu.tinycloud.user.config.TinyCloudUserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuxingyu01
 * @date 2022-12-08 10:40
 * @description
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TinyCloudUserConfig tinyCloudUserConfig;


    @RequestMapping("/testuser")
    public Result testuser(HttpServletRequest request) {
        log.info("userId = " + request.getHeader("USER_ID"));

        return Result.ok("调用成功", tinyCloudUserConfig.getName());
    }

}
