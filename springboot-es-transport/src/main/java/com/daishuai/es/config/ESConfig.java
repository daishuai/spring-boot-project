package com.daishuai.es.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description: ES配置
 * @Author: daishuai
 * @CreateDate: 2019/3/10 15:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ESProperties.class)
public class ESConfig  implements DisposableBean {

    @Autowired
    private ESProperties esProperties;

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        Settings setting = Settings.builder().put("cluster.name", esProperties.getClusterName())
                //增加嗅探机制，找到ES集群
                .put("client.transport.sniff", true)
                //增加线程池个数
                .put("thread_pool.search.size", esProperties.getPoolSize())
                .build();
        InetSocketTransportAddress inetSocketTransportAddress =
                new InetSocketTransportAddress(InetAddress.getByName(esProperties.getHostName()), Integer.valueOf(esProperties.getPort()));
        TransportClient client = new PreBuiltTransportClient(setting).addTransportAddress(inetSocketTransportAddress);
        return client;
    }


    @Override
    public void destroy() throws Exception {
        log.info("close ElasticSearch Client...");
        transportClient().close();
    }
}
