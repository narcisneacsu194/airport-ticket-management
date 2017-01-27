package com.teamtreehouse.airport.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
// This is a Spring configuration file that creates special database/backend beans.
// It makes extensive use of the application.properties file located in the main/resources folder.
@Configuration
@PropertySource("application.properties")
public class DataConfig {

    @Autowired
    private Environment env;

    // This is a Java Bean method that creates a Session factory object for autowiring.
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("airport.entity.package"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    // This is a Java Bean method that creates a Data Source for autowiring.
    @Bean
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("airport.db.driver"));
        ds.setUrl(env.getProperty("airport.db.url"));
        ds.setUsername(env.getProperty("airport.db.username"));
        ds.setPassword(env.getProperty("airport.db.password"));
        return ds;
    }
}
