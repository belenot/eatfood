package com.belenot.eatfood.test.web.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;
import com.belenot.eatfood.web.controller.AuthorozationController;
import com.belenot.eatfood.web.controller.FoodController;
import com.belenot.eatfood.web.interceptor.SessionInterceptor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

@TestInstance( Lifecycle.PER_CLASS )
@ExtendWith( { EatfoodServiceResolver.class, RandomDomainResolver.class  } )
public class SessionInterceptorTest {

    private SessionInterceptor sessionInterceptor;
    public void setSessionInterceptor(SessionInterceptor sessionInterceptor) {
	this.sessionInterceptor = sessionInterceptor;
    }
    
    @BeforeAll
    public void init() {
	MockEnvironment env = new MockEnvironment();
	if (sessionInterceptor == null) sessionInterceptor = new SessionInterceptor(env);
    }

    @Test
    public void preHandlePerMethod(@RandomDomain Client client) throws Exception {
	Class<AuthorozationController> klass = AuthorozationController.class;
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
	
	Method handlerMethod = klass.getMethod("logIn", String.class, String.class, HttpServletRequest.class);
	assertTrue(sessionInterceptor.preHandle(request, response, handlerMethod));
	assertNull(response.getRedirectedUrl());
	
	MockHttpSession session = new MockHttpSession();
	request.setSession(session);
	session.setAttribute("client", client);
	assertFalse(sessionInterceptor.preHandle(request, response, handlerMethod));
	assertNotNull(response.getRedirectedUrl());

        request = new MockHttpServletRequest();
	response = new MockHttpServletResponse();
	request.setSession(session);
	handlerMethod = klass.getMethod("logOut", Client.class, HttpSession.class);
	assertTrue(sessionInterceptor.preHandle(request, response, handlerMethod));
	session.removeAttribute("client");
	assertFalse(sessionInterceptor.preHandle(request, response, handlerMethod));
    }

    @Test
    public void preHandlerPerClass(@RandomDomain Client client) throws Exception {
	Class<FoodController> klass = FoodController.class;
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
	MockHttpSession session = new MockHttpSession();

	Method anyHandlerMethod = klass.getMethod("getFood", int.class);
	assertFalse(sessionInterceptor.preHandle(request, response, anyHandlerMethod));
	assertNotNull(response.getRedirectedUrl());

	session.setAttribute("client", client);
	request = new MockHttpServletRequest();
	response = new MockHttpServletResponse();
	request.setSession(session);
	assertTrue(sessionInterceptor.preHandle(request, response, anyHandlerMethod));
	assertNull(response.getRedirectedUrl());
    }
	
	
	

}
