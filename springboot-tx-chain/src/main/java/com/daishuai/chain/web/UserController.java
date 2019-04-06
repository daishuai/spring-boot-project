package com.daishuai.chain.web;

import com.daishuai.chain.domain.primary.User;
import com.daishuai.chain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id:\\d}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
}
