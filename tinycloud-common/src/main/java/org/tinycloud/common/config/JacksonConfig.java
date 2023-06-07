package org.tinycloud.common.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
 *
 * @author liuxingyu01
 * @since 2022-07-08 17:08
 **/
@Configuration
public class JacksonConfig {

    /**
     * 自定义序列化类型转换
     * 注：此处解决Long型转换后，前端js损失精度的问题，将Long型转换为字符串类型
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                // 若POJO对象的属性值为null，序列化时不进行显示（选配）
                // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);

                // 若POJO对象的属性值为""，序列化时不进行显示（选配）
                // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);

                // DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES相当于配置，JSON串含有未知字段时，反序列化依旧可以成功
                builder.failOnUnknownProperties(false);

                // 将Long和BigInteger类型转换为字符串类型（主要是为了解决前端js损失精度的问题）
                builder.serializerByType(Long.class, ToStringSerializer.instance)
                        .serializerByType(Long.TYPE, ToStringSerializer.instance)
                        .serializerByType(BigInteger.class, ToStringSerializer.instance);

                // 针对于Date类型，文本格式化
                builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 全局时区配置
                builder.timeZone("GMT+8");

                // 针对于JDK新时间类(LocalDateTime, LocalDate)。序列化时带有T的问题，自定义格式化字符串
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


                builder.modules(javaTimeModule);
            }
        };

        return customizer;
    }

}
