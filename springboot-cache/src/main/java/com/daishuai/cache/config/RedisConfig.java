package com.daishuai.cache.config;

import com.daishuai.cache.entity.Department;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/26 22:00
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate deptRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Department> redisTemplate = new RedisTemplate<Object, Department>();
        RedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Department> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }


}
