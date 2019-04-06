package com.daishuai.amqp.service;

import com.daishuai.amqp.entity.Department;
import com.daishuai.amqp.sender.ResultSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/27 18:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
@Slf4j
public class DeptService {

    @Autowired
    private ResultSender resultSender;

    @RabbitListener(queues = "rabbit.queue")
    public void receive(String request) throws InterruptedException {
        log.info("接收到请求：{}！", request);
        log.info("处理请求中....");
        Thread.sleep(3000);
        resultSender.sendResult(request);
    }
}
