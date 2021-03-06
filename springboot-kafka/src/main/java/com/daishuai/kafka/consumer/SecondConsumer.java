package com.daishuai.kafka.consumer;

import com.daishuai.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/5/5 21:10
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@Component
@Topic(value = "second_topic")
public class SecondConsumer implements Consumer {
    @Override
    public void handle() {
        log.info("secondConsumer>>>>>>>>>>>>>>>>>>>");
    }
}
