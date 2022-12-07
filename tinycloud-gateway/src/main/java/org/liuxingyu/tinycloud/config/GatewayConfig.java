package org.liuxingyu.tinycloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author liuxingyu01
 * @date 2022-12-07 11:12
 * @description Gateway网关配置
 **/
@Configuration
public class GatewayConfig {

    @Bean
    RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/webjars/**", new ClassPathResource("webjars/"));
    }


    /**
     * gateway跨域配置
     *
     * @return CorsWebFilter
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedMethod("GET,POST,PUT,DELETE,OPTIONS,HEAD");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("*");
        // 设置预检请求的缓存时间（秒），在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(18000L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
