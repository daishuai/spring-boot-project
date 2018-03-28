package com.daishuai.security.browser.web.service;

import com.daishuai.security.browser.vo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/28 12:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class BrowserUserDetailsService implements UserDetailsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = Logger.getLogger(BrowserUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(username);
        User user = new User();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //String name = ops.get("username");
        ops.set("password","123456");
        String password = ops.get("password");
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }
}
