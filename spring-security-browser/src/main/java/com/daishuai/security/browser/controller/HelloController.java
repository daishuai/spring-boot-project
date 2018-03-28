package com.daishuai.security.browser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/27 16:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object hello(){
        Map map = new HashMap();
        map.put("1", "zhangsan");
        map.put("2", "lisi");
        map.put("3",new Date());
        return map;
    }
}
