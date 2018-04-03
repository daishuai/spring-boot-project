package com.daishuai.jms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/4/3 19:01
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message){
        jmsTemplate.send(destination, new MyMessage(message));
    }
}
