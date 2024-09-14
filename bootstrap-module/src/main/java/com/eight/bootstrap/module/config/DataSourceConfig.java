package com.eight.bootstrap.module.config;

import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfig {

    @Bean(name = "userModuleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user-module")
    public DataSource userModuleDataSource() {
       return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderModuleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order-module")
    public DataSource orderModuleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productModuleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.product-module")
    public DataSource productModuleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            ObjectProvider<JpaVendorAdapter> jpaVendorAdapterProvider,
            ObjectProvider<PersistenceUnitManager> persistenceUnitManagerProvider) {
        JpaVendorAdapter jpaVendorAdapter = jpaVendorAdapterProvider.getIfAvailable(HibernateJpaVendorAdapter::new);
        return new EntityManagerFactoryBuilder(
                jpaVendorAdapter,
                Collections.emptyMap(),
                persistenceUnitManagerProvider.getIfAvailable());
    }
}