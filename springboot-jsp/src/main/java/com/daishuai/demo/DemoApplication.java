package com.daishuai.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/27 12:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
