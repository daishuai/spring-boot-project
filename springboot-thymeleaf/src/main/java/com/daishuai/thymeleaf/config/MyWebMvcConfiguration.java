package com.daishuai.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/5/13 22:28
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurerAdapter {


    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

}
