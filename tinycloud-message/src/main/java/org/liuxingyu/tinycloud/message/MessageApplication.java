package org.liuxingyu.tinycloud.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.liuxingyu.tinycloud.common.consts.GlobalConstant.BASE_PACKAGE_PREFIX;
import static org.liuxingyu.tinycloud.common.consts.GlobalConstant.FEIGN_PACKAGE_PREFIX;

/**
 * @author liuxingyu01
 * @date 2022-12-08 15:03
 * @description
 **/
@EnableDiscoveryClient // nacos注册中心配置
@EnableFeignClients(basePackages = {FEIGN_PACKAGE_PREFIX})
@SpringBootApplication(scanBasePackages = {BASE_PACKAGE_PREFIX})
public class MessageApplication {
    final static Logger logger = LoggerFactory.getLogger(MessageApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(MessageApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        logger.info("\n----------------------------------------------------------\n\t" +
                "tinycloud-message 启动成功！\n\t" +
                "┌─┐┬ ┬┌─┐┌─┐┌─┐┌─┐┌─┐  ┌─┐┌┬┐┌─┐┬─┐┌┬┐┌─┐┌┬┐   ┬\n\t" +
                "└─┐│ ││  │  ├┤ └─┐└─┐  └─┐ │ ├─┤├┬┘ │ ├┤  ││   │\n\t" +
                "└─┘└─┘└─┘└─┘└─┘└─┘└─┘  └─┘ ┴ ┴ ┴┴└─ ┴ └─┘─┴┘   o\n\t" +
                "-------------------------------------------------------------------------\n\t" +
                "Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "-------------------------------------------------------------------------");
    }
}
