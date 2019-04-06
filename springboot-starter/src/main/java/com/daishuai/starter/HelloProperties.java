package com.daishuai.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/11/11 14:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
