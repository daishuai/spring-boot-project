package com.daishuai.kafka.config;

import com.daishuai.kafka.common.MessageEntity;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/9 17:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaTemplate<String, MessageEntity> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageEntity>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(3);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ProducerFactory<String, MessageEntity> producerFactory() {
        ProducerFactory<String, MessageEntity> kafkaProducerFactory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(),
                new StringSerializer(),
                new JsonSerializer<MessageEntity>());
        return kafkaProducerFactory;
    }

    public ConsumerFactory<String, MessageEntity> consumerFactory() {
        ConsumerFactory<String, MessageEntity> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                new JsonDeserializer<>(MessageEntity.class));
        return kafkaConsumerFactory;
    }
}
