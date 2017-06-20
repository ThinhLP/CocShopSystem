package com.cocshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Nguyen Cong Chinh on 6/19/2017.
 */
@Configuration
public class DatabaseConfig {
    @Autowired
    private DataSource dataSource;
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.cocshop.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties addtionProperties = new Properties();
        addtionProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        addtionProperties.put("hibernate.show_sql", "true");
        addtionProperties.put("hibernate.hbm2ddl.auto","update");
        entityManagerFactoryBean.setJpaProperties(addtionProperties);
        return entityManagerFactoryBean;
    }
}
