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
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = {"com.daishuai.tx.domain.secondary"}
)
public class SecondaryJpaConfig {

    @Autowired
    @Qualifier("secondaryDatasource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "secondaryEntityManager")
    public EntityManager secondaryEntityManager(EntityManagerFactoryBuilder builder) {
        return secondaryEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties())
                .packages(new String[]{"com.daishuai.tx.domain.secondary"})
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    public Map<String, String> getVendorProperties() {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}
*/
