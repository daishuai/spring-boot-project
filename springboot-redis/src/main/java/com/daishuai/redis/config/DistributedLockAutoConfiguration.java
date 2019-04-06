package com.daishuai.redis.config;

import com.daishuai.redis.concurrent.lock.DistributedLock;
import com.daishuai.redis.concurrent.lock.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 分布式锁自动配置类
 * @Author: daishuai
 * @CreateDate: 2019/2/18 20:38
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnMissingBean(DistributedLock.class)
    public DistributedLock redisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }
}
