/*
package com.daishuai.tx.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Map;

*/
/**
 * @Description: Spring Data Jpa 第一数据源配置
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 *//*

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.daishuai.tx.domain.primary"}
)
public class PrimaryJpaConfig {

    @Autowired
    @Qualifier("primaryDatasource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "primaryEntityManager")
    public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder) {
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties())
                .packages(new String[]{"com.daishuai.tx.domain.primary"})
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    public Map<String, String> getVendorProperties() {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}
*/
