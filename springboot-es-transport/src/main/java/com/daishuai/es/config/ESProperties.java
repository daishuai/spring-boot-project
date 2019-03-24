package com.daishuai.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 16:06
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ESProperties {

    /**
     * ES集群地址
     */
    private String hostName;

    /**
     * 端口
     */
    private String port;

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 连接池大小
     */
    private Integer poolSize;

}
