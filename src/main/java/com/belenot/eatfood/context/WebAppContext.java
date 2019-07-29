package com.belenot.eatfood.context;

import java.text.SimpleDateFormat;
import java.util.List;

import com.belenot.eatfood.web.interceptor.SessionInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.core.internal.jobs.ObjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
	registry.addInterceptor(new SessionInterceptor(env));
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactoryConfig.sessionFactory());
	return transactionManager;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
	    .indentOutput(true)
	    .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
	converters.add(converter);
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
	registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
