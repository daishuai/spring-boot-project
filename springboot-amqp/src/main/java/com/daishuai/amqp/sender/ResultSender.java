package com.daishuai.amqp.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送消息到队列
 * @Author: daishuai
 * @CreateDate: 2018/8/30 23:06
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
@Component
public class ResultSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendResult(String result){
        rabbitTemplate.convertAndSend("rabbit.result", result);
        log.info("发送消息：{}，到队列：{}", result, "rabbit.result");
    }
}
