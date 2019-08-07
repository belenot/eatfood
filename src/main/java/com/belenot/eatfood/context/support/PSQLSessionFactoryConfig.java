package com.belenot.eatfood.context.support;

import java.util.Properties;

import javax.sql.DataSource;

import com.belenot.eatfood.context.SessionFactoryConfig;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

@Configuration
@Profile("default")
public class PSQLSessionFactoryConfig implements SessionFactoryConfig {

    private String defaultDbUrl = "jdbc:postgresql://localhost:8832/eatfood2?currentSchema=v0_2";
    
    @Autowired
    private Environment env;
    
    @Bean
    @Override
    public SessionFactory sessionFactory() {
	LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
	builder.scanPackages("com.belenot.eatfood.domain")
	    .addProperties(hibernateProperties());
	return builder.buildSessionFactory();

    }

    @Bean
    @Override
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	String dbUrl = env.getProperty("server.jdbc.connection", defaultDbUrl);
	dataSource.setUrl(dbUrl);
	dataSource.setUsername("eatfood");
	dataSource.setPassword("eatfood");
	return dataSource;
    }

    private final Properties hibernateProperties() {
	Properties hibernateProperties = new Properties();
	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
	return hibernateProperties;
    }
}

