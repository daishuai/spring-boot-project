package com.daishuai.kafka.producer;

import com.daishuai.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/9 17:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
@Slf4j
public class SimpleProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void send(String topic, String key, MessageEntity data) {
        kafkaTemplate.send(topic, key, data);
    }

    public void sendAndCallback(String topic, String key, MessageEntity data) {
        ProducerRecord<String, MessageEntity> producerRecord = new ProducerRecord<>(topic, key, data);
        ListenableFuture future = kafkaTemplate.send(producerRecord);
        future.addCallback(new ProducerCallback(System.currentTimeMillis()));
    }
}
