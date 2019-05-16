package com.daishuai.kafka.controller;

import com.daishuai.kafka.common.MessageEntity;
import com.daishuai.kafka.consumer.CommonConsumer;
import com.daishuai.kafka.consumer.Consumer;
import com.daishuai.kafka.producer.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/9 17:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private SimpleProducer simpleProducer;

    @Autowired
    private Map<String, Consumer> consumerMap;

    @Autowired
    private CommonConsumer commonConsumer;


    /**
     * 测试接口
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "ok";
    }


    @RequestMapping("/send")
    public String send(@RequestParam Map params) {
        MessageEntity message = new MessageEntity();
        message.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        message.setData(params);
        simpleProducer.sendAndCallback("mytopic", "params", message);
        return "success";
    }

    @GetMapping("/consumer")
    public void consumer() {
        log.info("consumer:{}", consumerMap.size());
    }
}
