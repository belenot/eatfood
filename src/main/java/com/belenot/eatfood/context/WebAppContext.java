package com.belenot.eatfood.context;

import com.belenot.eatfood.context.aspect.CritErrorAspect;
import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
public class WebAppContext implements WebMvcConfigurer {
    @Autowired
    public ConfigurableWebApplicationContext ctx;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Bean
    public ViewResolver viewResolver() {
	ViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
	
	return viewResolver;
    }

    @Bean( initMethod = "init", destroyMethod = "destroy" )
    ClientDao clientDao() {
	ClientDaoSql clientDao = new ClientDaoSql();
	clientDao.setConnectionAddress("jdbc:postgresql://localhost:8832/eatfood");
	clientDao.setUsername("eatfood");
	clientDao.setPassword("eatfood");
	return clientDao;
    }

    @Bean( initMethod = "init", destroyMethod = "destroy" )
    FoodDao foodDao() {
	FoodDaoSql foodDao = new FoodDaoSql();
	foodDao.setConnectionAddress("jdbc:postgresql://localhost:8832/eatfood");
	foodDao.setUsername("eatfood");
	foodDao.setPassword("eatfood");
	return foodDao;
    }

    @Bean
    public CritErrorAspect critErrorAspect() {
	CritErrorAspect critErrorAspect = new CritErrorAspect();
	critErrorAspect.setCtx(ctx);
	return new CritErrorAspect();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionInterceptor());
    }
}
