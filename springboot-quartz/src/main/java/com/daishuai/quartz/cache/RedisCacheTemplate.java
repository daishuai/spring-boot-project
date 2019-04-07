package com.daishuai.quartz.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/6 14:48
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class RedisCacheTemplate implements CacheTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void hashSet(String key, String hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, JSONObject.toJSONString(value));
    }

    @Override
    public <T> T hashGet(String key, String hashKey, Class<T> clazz) {
        Object value = stringRedisTemplate.opsForHash().get(key, hashKey);
        return JSONObject.parseObject(String.valueOf(value), clazz);
    }

    @Override
    public void zSet(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    public Set<String> zRangeByScore(String key, double min, double max) {
        Set<String> keys = stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
        return keys;
    }
}
