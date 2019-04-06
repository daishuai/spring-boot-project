package com.daishuai.kafka.consumer;

import com.daishuai.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/9 17:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
@Slf4j
public class SimpleConsumer {

    @KafkaListener(topics = "mytopic")
    public MessageEntity receive(ConsumerRecord<String, MessageEntity> record) {
        log.info("监听到一条消息：{}", record.value().getId());
        return record.value();
    }
}
