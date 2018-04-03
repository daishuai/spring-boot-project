package com.daishuai.jms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/4/3 18:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class Receiver {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private JmsTemplate jmsTemplate;


    public Object getMessage(String destination){
        Object obj = jmsTemplate.receiveAndConvert(destination);
        logger.info("消费了一条消息！！！！！！！！！！！");
        return obj;
    }
}
