package com.eight.user.module.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.eight.user.module.repository",
        entityManagerFactoryRef = "userModuleEntityManagerFactory",
        transactionManagerRef = "userModuleTransactionManager"
)
public class UserJpaConfig {

    @Autowired
    private EntityManagerFactoryBuilder builder;

    @Bean(name = "userModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userModuleEntityManagerFactory(
            @Qualifier("userModuleDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.eight.user.module.model")
                .persistenceUnit("userModule")
                .build();
    }

    @Bean(name = "userModuleTransactionManager")
    public PlatformTransactionManager userModuleTransactionManager(
            @Qualifier("userModuleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
