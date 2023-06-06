package org.liuxingyu.tinycloud.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * <p>
 * RedisTemplate配置
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-08 11:16:37
 */
@Configuration
public class RedisConfig {

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> initRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 定义 String 序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 定义 Jackson 序列化器
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化时智能识别变量名（识别没有按驼峰格式命名的变量名）
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 反序列化识别对象类型
        // objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        // 反序列化如果有多的属性，不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 反序列化如果碰到不识别的枚举值，是否作为空值解释，true:不会抛不识别的异常, 会赋空值，false:会抛不识别的异常
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置 Redis 的 key 以及 hash 结构的 field 使用 String 序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 设置 Redis 的 value 以及 hash 结构的 value 使用 Jackson 序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisClient initRedisClient(RedisTemplate<String, Object> redisTemplate) {
        return new RedisClient(redisTemplate);
    }
}
