package com.belenot.eatfood;

import com.belenot.eatfood.context.WebAppContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringConfigurationTest {

    private static AnnotationConfigWebApplicationContext ctx;

    @BeforeEach
    public void initApplicationContext() {
	ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(WebAppContext.class);
	ctx.refresh();
    }	 


    @Test
    public void ApplicationContext_NotNull() {
	assert(ctx != null);
    }
    
    @Test
    public void SessionFactoryNotNull() {
	assert(ctx.getBean("sessionFactory") != null);
    }

    @AfterEach
    public void closeApplicationContext() {
	ctx.close();
    }
	
	
}
