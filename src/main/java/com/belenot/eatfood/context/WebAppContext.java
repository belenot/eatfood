package com.belenot.eatfood.context;

import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan( "com.belenot.eatfood" )
@PropertySource( "classpath:server.properties" )
public class WebAppContext implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Autowired
    private SessionFactoryConfig sessionFactoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations(env.getProperty("server.resources.path", "/resources/"));
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionInterceptor());
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactoryConfig.sessionFactory());
	return transactionManager;
    }
}
