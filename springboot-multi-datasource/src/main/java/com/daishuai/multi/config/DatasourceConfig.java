package com.daishuai.multi.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description: 多数据源配置
 * @Author: daishuai
 * @CreateDate: 2019/1/15 11:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class DatasourceConfig {

    @Bean(name = "mysqlDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDatasource() {
        return DataSourceBuilder.create().build();
    }

}
