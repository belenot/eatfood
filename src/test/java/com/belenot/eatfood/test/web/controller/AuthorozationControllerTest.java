package com.belenot.eatfood.test.web.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.AuthorizationService;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;
import com.belenot.eatfood.test.mock.service.MockAuthorizationService;
import com.belenot.eatfood.web.controller.AuthorozationController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockHttpServletRequest;

@TestMethodOrder( OrderAnnotation.class )
@TestInstance( Lifecycle.PER_CLASS )
@ExtendWith( RandomDomainResolver.class )
public class AuthorozationControllerTest {
    
    private AuthorozationController authorozationController;
    private HttpSession httpSession;
    private Client originClient;

    public void setAuthorozationController(AuthorozationController authorozationController) { this.authorozationController = authorozationController; }

    @BeforeAll
    public void init(@RandomDomain Client client) {
        originClient = client;
	originClient.setId(1);
        authorozationController = new AuthorozationController();
	authorozationController.setAuthorizationService(new MockAuthorizationService());
    }

    @Test
    @Order( 1 )
    public void registerTest(@RandomDomain Client client) {
	originClient = client;
	HttpServletRequest request = new MockHttpServletRequest();
	boolean result = assertDoesNotThrow( () -> authorozationController.register(client, request));
	assertTrue(result);
        Exception exc = assertThrows(Exception.class,  () -> authorozationController.register(client, request));
	assertNotNull(exc);
    }
    
    @Test
    @Order( 2 )
    public void logInTest() {
	HttpServletRequest request = new MockHttpServletRequest();
        boolean result = assertDoesNotThrow( () -> authorozationController.logIn(originClient.getLogin(), originClient.getPassword(), request));
	assertTrue(result);
	httpSession = request.getSession(false);
	assertNotNull(httpSession);
	assertNotNull(httpSession.getAttribute("client"));
	assertEquals(((Client)httpSession.getAttribute("client")).getId(), originClient.getId());
	result = assertDoesNotThrow( () -> authorozationController.logIn("exactlyNotCorrectLogin", "exactlyNotCorrectPassword", new MockHttpServletRequest()));
	assertFalse(result);
    }

    @Test
    @Order( 3 )
    public void logOutTest(@RandomDomain Client otherClient) {
	Client client = (Client) httpSession.getAttribute("client");
        boolean result = assertDoesNotThrow( () -> authorozationController.logOut(client, httpSession));
	assertTrue(result);
	assertNull(httpSession.getAttribute("client"));

	otherClient.setId(2);
	result = assertDoesNotThrow( () -> authorozationController.logOut(otherClient, httpSession));
	assertFalse(result);
    }

}
