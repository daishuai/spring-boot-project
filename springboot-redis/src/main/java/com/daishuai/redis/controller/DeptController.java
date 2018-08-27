package com.daishuai.redis.controller;

import com.daishuai.redis.entity.Department;
import com.daishuai.redis.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/15 12:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class DeptController {

    @Autowired
    private DeptRepository repository;


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/all")
    public Object addList(){
        //序列化器
        RedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        //高并发下，有问题：缓存穿透（）
        //先查询缓存
        Object all = redisTemplate.opsForValue().get("allDepartments");

        //缓存中没有，再查询数据库
        if (all == null) {
            all = repository.findAll();
            redisTemplate.opsForValue().set("allDepartments" ,all);
        }
        return all;
    }
}
