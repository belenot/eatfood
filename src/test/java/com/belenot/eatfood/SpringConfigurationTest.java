package com.belenot.eatfood;

import com.belenot.eatfood.context.WebAppContext;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@TestMethodOrder( OrderAnnotation.class )
@TestInstance( Lifecycle.PER_CLASS )
public class SpringConfigurationTest {

    private static AnnotationConfigWebApplicationContext ctx;
    
    @BeforeAll
    public void initApplicationContext() throws Exception {
	ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(WebAppContext.class);
	ctx.setServletContext(new MockServletContext());
	ctx.refresh();
    }	 

    @Test
    @Order( 1 )
    public void ApplicationContextNotNull() {
	assert(ctx != null);
    }
    
    @Test
    @Order( 2 )
    public void SessionFactoryNotNull() {
	assert(ctx.getBean("sessionFactory") != null);
    }

    @AfterAll
    public void closeApplicationContext() {
	ctx.close();
    }
	
	
}
