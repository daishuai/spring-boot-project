package com.daishuai.amqp.service;

import com.daishuai.amqp.entity.Department;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/27 18:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
public class DeptService {

    @RabbitListener(queues = "rabbit.queue")
    public void receive(Department department){
        System.out.println(department);
    }
}
