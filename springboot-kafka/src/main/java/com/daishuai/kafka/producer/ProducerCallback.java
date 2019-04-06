package com.daishuai.kafka.producer;

import com.daishuai.kafka.common.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 14:37
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class ProducerCallback implements ListenableFutureCallback<SendResult<String, MessageEntity>> {

    private Long startTime;

    public ProducerCallback(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void onSuccess(SendResult<String, MessageEntity> sendResult) {
        long endTime = System.currentTimeMillis();
        log.info("message send success, take {} milliseconds", endTime - startTime);
        RecordMetadata metadata = sendResult.getRecordMetadata();
        log.info("the topic is {}, partition: {}, offset: {}, keySize: {}, valueSize: {}, take {} milliseconds",
                metadata.topic(),
                metadata.partition(),
                metadata.offset(),
                metadata.serializedKeySize(),
                metadata.serializedValueSize(),
                endTime - metadata.timestamp());
    }
}
