package com.daishuai.kafka.consumer;

import com.daishuai.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/27 11:39
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@Component
public class OtherConsumer {

    @KafkaListener(groupId = "consumerGroup", topicPartitions = {@TopicPartition(topic = "mytopic", partitions = {"1","0"})})
    public MessageEntity receiveMessage(ConsumerRecord<String, MessageEntity> record) {
        log.info("OtherConsumer监听到一条消息,topic:{},key:{},offset:{},partition:{},id:{}", record.topic(), record.key(), record.offset(), record.partition(), record.value().getId());
        return record.value();
    }
}
