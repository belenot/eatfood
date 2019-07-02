package com.belenot.eatfood.context;

import com.belenot.eatfood.context.aspect.CritErrorAspect;
import com.belenot.eatfood.context.aspect.LoggingAspect;
import com.belenot.eatfood.dao.AccountDaoSql;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.dao.FoodListDaoSql;
import com.belenot.eatfood.service.DaoService;
import com.belenot.eatfood.service.DaoSqlService;
import com.belenot.eatfood.web.interceptor.EncodingInterceptor;

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

    //Change init on event when context is fully loaded
    @Bean ( initMethod = "init" )
    public DaoService<?> daoSqlService() {
	DaoSqlService daoSqlService = new DaoSqlService();
	daoSqlService.setConnectionAddress("jdbc:postgresql://localhost:8832/eatfood2");
	daoSqlService.setUsername("eatfood");
	daoSqlService.setPassword("eatfood");
	daoSqlService.setClientDaoSql(clientDaoSql());
	daoSqlService.setAccountDaoSql(accountDaoSql());
	daoSqlService.setFoodDaoSql(foodDaoSql());
	daoSqlService.setFoodListDaoSql(foodListDaoSql());
	
	return daoSqlService;
    }

    @Bean
    public ClientDaoSql clientDaoSql() {
	ClientDaoSql clientDaoSql = new ClientDaoSql();
	return clientDaoSql;
    }
    @Bean
    public AccountDaoSql accountDaoSql() {
	AccountDaoSql accountDaoSql = new AccountDaoSql();
	return accountDaoSql;
    }
    @Bean
    public FoodDaoSql foodDaoSql() {
	FoodDaoSql foodDaoSql = new FoodDaoSql();
	return foodDaoSql;
    }
    @Bean
    public FoodListDaoSql foodListDaoSql() {
	FoodListDaoSql foodListDaoSql = new FoodListDaoSql();
	return foodListDaoSql;
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
	//registry.addInterceptor(new SessionInterceptor());
    }
}
