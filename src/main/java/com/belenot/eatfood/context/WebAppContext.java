package com.belenot.eatfood.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan( "com.belenot.eatfood" )
public class WebAppContext implements WebMvcConfigurer {
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionInterceptor());
    }
}
