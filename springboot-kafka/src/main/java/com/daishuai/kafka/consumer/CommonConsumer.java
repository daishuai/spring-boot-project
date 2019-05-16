package com.daishuai.kafka.consumer;

import com.daishuai.kafka.annotation.Topic;
import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/5/5 21:16
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class CommonConsumer implements InitializingBean {

    private Map<String, Consumer> consumerMap;

    @Autowired
    private List<Consumer> consumers;

    @Override
    public void afterPropertiesSet() throws Exception {
        consumerMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(consumers)) {
            consumers.forEach(consumer -> {
                Topic annotation = consumer.getClass().getAnnotation(Topic.class);
                String topicName = annotation.value();
                consumerMap.put(topicName, consumer);
            });
        }
    }

    @KafkaListener(topics = {"first_topic","second_topic"})
    public void receive(ConsumerRecord<String, Object> record) {
        String topic = record.topic();
        Consumer consumer = consumerMap.get(topic);
        consumer.handle();
    }
}
