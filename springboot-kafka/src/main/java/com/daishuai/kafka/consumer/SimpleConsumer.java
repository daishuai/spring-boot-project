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
 * @CreateDate: 2019/3/9 17:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
@Slf4j
public class SimpleConsumer {

    @KafkaListener(groupId = "consumerGroup", topicPartitions = {@TopicPartition(topic = "mytopic", partitions = {"0","1"})})
    public MessageEntity receive(ConsumerRecord<String, MessageEntity> record) {
        log.info("SimpleConsumer监听到一条消息,topic:{},key:{},offset:{},partition:{},id:{}", record.topic(), record.key(), record.offset(), record.partition(), record.value().getId());
        return record.value();
    }

}
