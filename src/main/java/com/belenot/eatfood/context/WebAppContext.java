package com.belenot.eatfood.context;

import com.belenot.eatfood.context.aspect.CritErrorAspect;
import com.belenot.eatfood.context.aspect.LoggingAspect;
import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
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
@ComponentScan( "com.belenot.eatfood" )
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

    @Bean( initMethod = "init", destroyMethod = "destroy" )
    public ClientDao clientDao() {
	ClientDaoSql clientDao = new ClientDaoSql();
	clientDao.setConnectionAddress(env.getProperty("server.jdbc.connection"));
	clientDao.setUsername(env.getProperty("server.jdbc.username"));
	clientDao.setPassword(env.getProperty("server.jdbc.password"));
	clientDao.setManualRegistre(new Boolean(env.getProperty("server.jdbc.manualRegistre")));
	return clientDao;
    }

    @Bean( initMethod = "init", destroyMethod = "destroy" )
    public FoodDao foodDao() {
	FoodDaoSql foodDao = new FoodDaoSql();
	foodDao.setConnectionAddress(env.getProperty("server.jdbc.connection"));
	foodDao.setUsername(env.getProperty("server.jdbc.username"));
	foodDao.setPassword(env.getProperty("server.jdbc.password"));
	foodDao.setManualRegistre(new Boolean(env.getProperty("server.jdbc.manualRegistre")));
	return foodDao;
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
	loggingAspect.setLogger(logger());
	return loggingAspect;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionInterceptor());
    }
}
