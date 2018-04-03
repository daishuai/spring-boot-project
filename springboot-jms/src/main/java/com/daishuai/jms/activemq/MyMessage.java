package com.daishuai.jms.activemq;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.logging.Logger;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/4/3 18:27
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MyMessage implements MessageCreator {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private String message;

    public MyMessage(String message){
        this.message = message;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        logger.info("JMS创建一条消息！！！！！！！！！！！！！");
        return session.createTextMessage(this.message);
    }
}
