package com.daishuai.tx.config;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 15:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class DatasourceConfig {

    @Primary
    @Bean(name = "primaryDatasourceProperties")
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
    public DataSource primaryDatasource(@Qualifier("primaryDatasourceProperties") DataSourceProperties properties) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(properties.getUrl());
        mysqlXADataSource.setUser(properties.getUsername());
        mysqlXADataSource.setPassword(properties.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        //将本地事务注册到Atomikos全局事务
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("primaryDatasource");
        //return DataSourceBuilder.create().build();
        return atomikosDataSourceBean;
    }

    @Bean(name = "secondaryDatasource")
    public DataSource secondaryDatasource(@Qualifier("secondaryDatasourceProperties") DataSourceProperties properties) throws SQLException {
        OracleXADataSource oracleXADataSource = new OracleXADataSource();
        oracleXADataSource.setURL(properties.getUrl());
        oracleXADataSource.setUser(properties.getUsername());
        oracleXADataSource.setPassword(properties.getPassword());

        //将本地事务注册到Atomikos全局事务
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(oracleXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("secondaryDatasource");
        //return DataSourceBuilder.create().build();
        return atomikosDataSourceBean;
    }


}
