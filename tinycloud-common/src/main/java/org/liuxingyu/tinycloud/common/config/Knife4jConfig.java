package org.liuxingyu.tinycloud.common.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置类（swagger2）
 * @author liuxingyu01
 * @since  2022-12-14 16:37
 **/
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    // 配置是否开启swagger-ui，默认为false
    @Value("${tinycloud.swagger-enable:false}")
    private Boolean enable;

    @Bean(value = "docketBean")
    public Docket docketBean() {
        // 指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        // 描述字段支持Markdown语法
                        .title("tinycloud微服务平台")
                        .description("# tinycloud微服务平台 接口文档")
                        .termsOfServiceUrl("https://www.tinycloud.xyz/")
                        .contact(new Contact("liuxingyu01", "", "liuxingyu9725@foxmail.com"))
                        .version("1.0.0")
                        .build())
                .enable(enable) // 是否开启
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}
