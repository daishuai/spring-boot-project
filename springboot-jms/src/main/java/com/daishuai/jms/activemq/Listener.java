package com.daishuai.jms.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/20 10:15
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "queue.test", containerFactory = "jmsQueueListenerContainerFactory")
    public void getQueueMessage(String text) {
        logger.info("监听到消息：{}", text);
    }

    @JmsListener(destination = "topic.test", containerFactory = "jmsTopicListenerContainerFactory")
    public void getTopicMessage(String text) {
        logger.info("订阅号推送消息：{}", text);
    }
}
