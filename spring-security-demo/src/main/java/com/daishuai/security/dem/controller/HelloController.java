package com.daishuai.security.dem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/26 22:47
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object hello(){
        Map map = new HashMap();
        map.put("1","zhangsan");
        map.put("2","lisi");
        return map;
    }
}
