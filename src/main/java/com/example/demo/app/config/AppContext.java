package com.example.demo.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// Класс интеграции Spring и Hibernate
// с использованием конфигурации Spring на основе Java.
// Конфигурация Hibernate на основе Java.

@Configuration
@PropertySource("classpath:database.properties")
// @EnableTransactionManagement включает возможности Spring
// по управлению транзакциями на основе аннотаций.
@EnableTransactionManagement
public class AppContext {

    @Autowired
    private Environment environment;

    // LocalSessionFactoryBean создает Hibernate SessionFactory.
    // Это способ настроить общую Hibernate SessionFactory
    // в контексте приложения Spring.
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.demo.app.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // https://docs.jboss.org/hibernate/orm/6.0/userguide/html_single/Hibernate_User_Guide.html#_sql_statement_logging
        // 26.10.1. SQL statement logging
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        // hibernate.hbm2ddl.auto автоматически проверяет или экспортирует схему DDL
        // в базу данных при создании SessionFactory.
        // С помощью create-drop схема базы данных будет удалена при явном закрытии SessionFactory.
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    // HibernateTransactionManager привязывает Hibernate Session
    // из указанной фабрики к потоку, что потенциально позволяет
    // использовать одну сессию, привязанную к потоку, на фабрику.
    // Этот диспетчер транзакций подходит для приложений, которые
    // используют одну Hibernate SessionFactory для доступа к транзакционным
    // данным, но он также поддерживает прямой доступ к источнику данных
    // внутри транзакции, т. е. простой JDBC.
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
