package com.daishuai.chain.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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

    @Bean(name = "primaryDatasourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSourceProperties primaryDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "secondaryDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSourceProperties secondaryDatasourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "primaryDatasource")
    @Primary
    public DataSource primaryDatasource() {
        return getDataSource(primaryDatasourceProperties());
    }

    @Bean(name = "secondaryDatasource")
    public DataSource secondaryDatasource() {
        return getDataSource(secondaryDatasourceProperties());
    }


    @Bean(name = "primaryJdbcTemplate")
    @Primary
    public JdbcTemplate primaryJdbcTemplate() {
        return new JdbcTemplate(primaryDatasource());
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate() {
        return new JdbcTemplate(secondaryDatasource());
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager primaryTM = new DataSourceTransactionManager(primaryDatasource());
        DataSourceTransactionManager secondaryTM = new DataSourceTransactionManager(secondaryDatasource());
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(primaryTM, secondaryTM);
        return chainedTransactionManager;
    }

    private DataSource getDataSource(DataSourceProperties dataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

        return dataSource;
    }
}
