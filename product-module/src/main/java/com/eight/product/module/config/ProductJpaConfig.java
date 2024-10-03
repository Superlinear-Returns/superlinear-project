package com.eight.product.module.config;
import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.eight.product.module.repository",
        entityManagerFactoryRef = "productModuleEntityManagerFactory",
        transactionManagerRef = "productModuleTransactionManager"
)
public class ProductJpaConfig {
    @Autowired
    private EntityManagerFactoryBuilder builder;

    @Bean(name = "productModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productModuleEntityManagerFactory(
            @Qualifier("productModuleDataSource") DataSource dataSource)
    {
        return builder.dataSource(dataSource)
                .packages("com.eight.product.module.model")
                .persistenceUnit("productModule")
                .build();
    }

    @Bean(name = "productModuleTransactionManager")
    public PlatformTransactionManager productModuleTransactionManager(
            @Qualifier("productModuleEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }



}
