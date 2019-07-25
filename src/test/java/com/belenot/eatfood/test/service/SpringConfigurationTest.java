package com.belenot.eatfood.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.belenot.eatfood.context.WebAppContext;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.service.DoseService;
import com.belenot.eatfood.service.FoodService;

import org.hibernate.SessionFactory;
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
    public void applicationContextNotNull() {
	assertNotNull(ctx);
    }
    
    @Test
    @Order( 2 )
    public void sessionFactoryNotNull() {
	SessionFactory sessionFactory = assertDoesNotThrow(() -> ctx.getBean(SessionFactory.class));
	assertNotNull(sessionFactory);
    }

    @Test
    @Order( 2 )
    public void serviceConfigured() {
	ClientService clientService = assertDoesNotThrow( () -> ctx.getBean(ClientService.class));
	assertNotNull(clientService);
	assertNotNull(clientService.getSessionFactory());
	assertTrue(clientService.getSessionFactory().isOpen());
	FoodService foodService = assertDoesNotThrow( () -> ctx.getBean(FoodService.class));
	assertNotNull(foodService);
	assertNotNull(foodService.getSessionFactory());
	assertTrue(foodService.getSessionFactory().isOpen());
	DoseService doseService = assertDoesNotThrow( () -> ctx.getBean(DoseService.class));
	assertNotNull(doseService);
	assertNotNull(doseService.getSessionFactory());
	assertTrue(doseService.getSessionFactory().isOpen());
    }


    @AfterAll
    public void closeApplicationContext() {
	ctx.close();
    }

    
	
}
