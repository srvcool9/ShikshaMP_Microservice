package com.netlink.rsk.visit_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "studentEntityManagerBean",
        basePackages = {"com.netlink.rsk.visit-service.studentdb.repositories"},
        transactionManagerRef = "studentTransactionManager")
public class StudentDBConfig {

    @Value("${second.datasource.url}")
    private String dbURL;

    @Value("${second.datasource.username}")
    private String dbUserName;

    @Value("${second.datasource.password}")
    private String dbPassword;

    @Value("${second.datasource.driver-class-name}")
    private String driverName;



    @Bean(name = "studentDataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setUrl(dbURL);
        dataSource.setDriverClassName(driverName);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean("studentEntityManagerBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter adapter= new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        bean.setDataSource(dataSource());
        bean.setPackagesToScan("com.netlink.rsk.visit-service.studentdb.models");
        Map<String, String > props= new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
        props.put("hibernate.show_sql","false");
        bean.setJpaPropertyMap(props);
        return bean;
    }

    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager= new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }


}