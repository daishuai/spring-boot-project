package com.daishuai.multi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Spring Data Jpa 第一数据源配置
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        //配置Repository所在位置
        basePackages = {"com.daishuai.multi.web.dao.mysql"}
)
public class MySQLJpaConfig {

    @Autowired
    @Qualifier("mysqlDatasource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Value("${spring.jpa.hibernate.mysql-dialect}")
    private String mysqlDialect;

    @Primary
    @Bean(name = "mysqlEntityManager")
    public EntityManager mysqlEntityManager(EntityManagerFactoryBuilder builder) {
        return mysqlEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(mysqlEntityManagerFactory(builder).getObject());
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties())
                //配置entity所在位置
                .packages(new String[]{"com.daishuai.multi.web.entity.mysql"})
                .persistenceUnit("mysqlPersistenceUnit")
                .build();
    }

    public Map<String, String> getVendorProperties() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hibernate.dialect", mysqlDialect);
        jpaProperties.setProperties(map);
        return jpaProperties.getHibernateProperties(dataSource);
    }
}

