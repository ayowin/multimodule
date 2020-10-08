package com.ouyangwei.multimodule.dao.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        /* String序列化器 */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        /* Jackson2Json序列化器 */
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        /* 使用String序列化器作为key的序列化与反序列化技术 */
        redisTemplate.setKeySerializer(stringRedisSerializer);

        /**
         * 方式一（扩展的角度更推荐）：
         * 使用String序列化器作为value的序列化与反序列化技术
         * 虽不可直接序列化和反序列化成pojo对象，但无任何依赖，
         * 可自由对接任何基于字符串的序列化与反序列化技术
         * 适用场景：redis可能存多种格式的value，如：xml、json、yml等
         **/
        redisTemplate.setValueSerializer(stringRedisSerializer);
        /**
         * 方式二：
         * 使用Jaskson序列化器作为value的序列化与反序列化技术:
         * 可直接序列化和反序列化成pojo对象
         * 注意：需依赖Jackson的json库
         * 适用场景：redis的value只用于pojo的序列化与反序列化
         **/
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
