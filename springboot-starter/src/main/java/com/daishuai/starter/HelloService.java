package com.daishuai.starter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/11/11 14:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String getName(){
        return helloProperties.getName();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
