package com.belenot.eatfood.context;

import com.belenot.eatfood.context.aspect.LoggingAspect;
import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.DoseDao;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.service.DaoService;
import com.belenot.eatfood.service.DaoServiceDefault;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan( "com.belenot.eatfood.aspect, com.belenot.eatfood.web" )
@PropertySource( "classpath:server.properties" )
public class WebAppContext implements WebMvcConfigurer {

    @Autowired
    public ConfigurableWebApplicationContext ctx;

    @Autowired
    public Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations(env.getProperty("server.resources.path", "/resources/"));
    }
    
    @Bean
    public ViewResolver viewResolver() {
	ViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
	return viewResolver;
    }
    @Bean
    public SessionFactory sessionFactory() {
	StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	Metadata metadata = new MetadataSources(registry).buildMetadata();
	SessionFactory sessionFactory = metadata.buildSessionFactory();
	return sessionFactory;	
    }
    @Bean
    public DaoService daoService() {

	return new DaoServiceDefault();
    }
    @Bean
    public JdbcConnectionFactory jdbcConnectionFactory() {
	JdbcConnectionFactory jdbcConnectionFactory = new JdbcConnectionFactory();
	jdbcConnectionFactory.setAddress("jdbc:postgresql://localhost:8832/eatfood2?currentSchema=v0_2");
	jdbcConnectionFactory.setUsername("eatfood");
	jdbcConnectionFactory.setPassword("eatfood");
	return jdbcConnectionFactory;
    }
    @Bean
    public ClientDao clientDao() {

	return daoService();
    }
    @Bean
    public FoodDao foodDao() {

	return daoService();
    }
    @Bean
    public DoseDao doseDao() {

	return daoService();
    }
    @Bean
    public Logger logger() {
	Logger logger = LogManager.getLogger();
	return logger;
    }
    @Bean
    public LoggingAspect loggingAspect() {
	LoggingAspect loggingAspect = new LoggingAspect();
	return loggingAspect;
    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("eatfoodFormat");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionInterceptor());
    }
}
