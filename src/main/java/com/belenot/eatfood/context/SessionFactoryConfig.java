package com.belenot.eatfood.context;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface SessionFactoryConfig {
    @Bean
    SessionFactory sessionFactory();
    @Bean
    DataSource dataSource();
}
