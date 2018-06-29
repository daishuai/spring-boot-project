package com.daishuai.task.controller;

import com.daishuai.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/29 14:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class AsyncController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/async")
    public Object hello(){
        taskService.asyncGet();
        return ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>hello world!!!!";
    }
}
