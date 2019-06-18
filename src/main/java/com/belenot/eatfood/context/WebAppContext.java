package com.belenot.eatfood.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.ClientDaoSql;

@Configuration
@EnableWebMvc
@ComponentScan( "com.belenot.eatfood" )
public class WebAppContext {
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
}
