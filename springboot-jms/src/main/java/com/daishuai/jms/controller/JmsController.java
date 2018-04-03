package com.daishuai.jms.controller;

import com.daishuai.jms.activemq.MyMessage;
import com.daishuai.jms.activemq.Receiver;
import com.daishuai.jms.activemq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/4/3 18:24
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class JmsController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private Receiver receiver;
    @Autowired
    private Sender sender;

    @RequestMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){
        sender.sendMessage("queue-test",message);
    }


    @RequestMapping("/get/{destination}")
    public Object getMessage(@PathVariable String destination){

        return receiver.getMessage(destination);
    }
}
