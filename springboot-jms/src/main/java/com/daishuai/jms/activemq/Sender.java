package com.daishuai.jms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier(value = "jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    @Qualifier(value = "jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    public void sendQueueMessage(String destination, String message){
        jmsQueueTemplate.send(destination, new MyMessage(message));
        jmsTopicTemplate.sendAndReceive(destination, new MyMessage(message));
    }

    public void sendTopicMessage(String destination, String message) {
        jmsTopicTemplate.send(destination, new MyMessage(message));
    }
}
