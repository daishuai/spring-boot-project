package com.daishuai.curator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/21 14:47
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class ZookeeperConfig {

    @Bean
    public ZookeeperLock zookeeperLock() {
        return new ZookeeperLock();
    }

    @Bean
    public CuratorDistributedLock curatorDistributedLock() {
        return new CuratorDistributedLock();
    }
}
