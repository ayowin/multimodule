package com.ouyangwei.multimodule.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();

        /* String序列化器 */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        /* Jackson2Json序列化器 */
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        /* 使用String序列化器作为key的序列化与反序列化技术 */
        redisTemplate.setKeySerializer(stringRedisSerializer);

        /**
         * 使用String序列化器作为value的序列化与反序列化技术
         * 虽不可直接序列化和反序列化成pojo对象，但无任何依赖，可自由对接任何json库
         **/
        redisTemplate.setValueSerializer(stringRedisSerializer);
        /**
         * 使用Jaskson序列化器作为value的序列化与反序列化技术:
         * 可直接序列化和反序列化成pojo对象
         * 注意：需依赖Jackson的json库
         **/
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
