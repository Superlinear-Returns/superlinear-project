package com.eight.order.module.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.eight.order.module.repository",
        entityManagerFactoryRef = "orderModuleEntityManagerFactory",
        transactionManagerRef = "orderModuleTransactionManager"
)
public class OrderJpaConfig {

    @Autowired
    private EntityManagerFactoryBuilder builder;

    @Bean(name = "orderModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean orderModuleEntityManagerFactory(
            @Qualifier("orderModuleDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.eight.order.module.model")
                .persistenceUnit("orderModule")
                .build();
    }

    @Bean(name = "orderModuleTransactionManager")
    public PlatformTransactionManager orderModuleTransactionManager(
            @Qualifier("orderModuleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
