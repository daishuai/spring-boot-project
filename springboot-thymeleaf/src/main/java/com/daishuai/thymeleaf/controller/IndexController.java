package com.daishuai.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/5/13 22:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
