package com.daishuai.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/20 10:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class JmsConfig {

    /**
     * 用于发送消息到队列
     * @param connectionFactory
     * @return
     */
    @Bean
    @Primary
    public JmsTemplate jmsQueueTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        //默认false
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }

    /**
     * 用于发送消息到主题
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsTemplate jmsTopicTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        //默认false
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    /**
     * 开启Pub/Sub模式
     * @param connectionFactory
     * @return
     */
    @Bean
    @Primary
    public JmsListenerContainerFactory jmsQueueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    /**
     * JMS默认开启P2P模式
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}
