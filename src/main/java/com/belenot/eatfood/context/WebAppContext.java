package com.belenot.eatfood.context;

import java.nio.charset.Charset;
import java.util.List;

import com.belenot.eatfood.context.aspect.CritErrorAspect;
import com.belenot.eatfood.context.aspect.LoggingAspect;
import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.DoseDao;
import com.belenot.eatfood.dao.DoseDaoSql;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.service.DaoService;
import com.belenot.eatfood.service.DaoServiceImpl;
import com.belenot.eatfood.web.interceptor.EncodingInterceptor;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
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
    public DaoService daoService() {
	DaoServiceImpl daoServiceImpl = new DaoServiceImpl();
	daoServiceImpl.setClientDao(clientDao());
	daoServiceImpl.setFoodDao(foodDao());
	daoServiceImpl.setDoseDao(doseDao());
	return daoServiceImpl;
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
	ClientDaoSql clientDao = new ClientDaoSql();
	clientDao.setConnection(jdbcConnectionFactory().getConnection());
	return clientDao;
    }
    @Bean
    public FoodDao foodDao() {
	FoodDaoSql foodDao = new FoodDaoSql();
	foodDao.setClientDao(clientDao());
	foodDao.setConnection(jdbcConnectionFactory().getConnection());
	return foodDao;
    }
    @Bean
    public DoseDao doseDao() {
	DoseDaoSql doseDao = new DoseDaoSql();
	doseDao.setFoodDao(foodDao());
	doseDao.setConnection(jdbcConnectionFactory().getConnection());
	return doseDao;
    }
    @Bean
    public Logger logger() {
	Logger logger = LogManager.getLogger();
	return logger;
    }
    @Bean
    public CritErrorAspect critErrorAspect() {
	CritErrorAspect critErrorAspect = new CritErrorAspect();
	critErrorAspect.setCtx(ctx);
	return new CritErrorAspect();
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
	registry.addInterceptor(new EncodingInterceptor());
	registry.addInterceptor(new SessionInterceptor());
    }
    /**
     * Need because of ResponseBody setting default to ISO-8...-1
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	for (HttpMessageConverter converter : converters) {
	    if (converter instanceof AbstractHttpMessageConverter) {
		((AbstractHttpMessageConverter) converter).setDefaultCharset(Charset.forName("UTF-8"));
	    }
	}
    }
}
