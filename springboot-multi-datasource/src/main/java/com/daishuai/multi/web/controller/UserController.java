package com.daishuai.multi.web.controller;

import com.daishuai.multi.web.common.ResponseMessage;
import com.daishuai.multi.web.entity.mysql.User;
import com.daishuai.multi.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/24 21:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseMessage getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return ResponseMessage.ok(user);
    }
}
