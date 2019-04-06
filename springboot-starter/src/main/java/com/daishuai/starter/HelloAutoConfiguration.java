package com.daishuai.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/11/11 14:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@ConditionalOnProperty(name = "hello.enable", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Autowired
    private HelloProperties helloProperties;

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService(){

        return new HelloService();
    }
}
