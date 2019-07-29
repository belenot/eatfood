package com.belenot.eatfood.web.interceptor;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    public SessionInterceptor() { }
    public SessionInterceptor(Environment env) {
	defaultRedirect = env.getProperty("defaultRedirect", defaultRedirect);
	authorizedRedirect = env.getProperty( "authorizedRedirect", authorizedRedirect);
    }
    
    private String defaultRedirect = "/eatfood/registration";
    private String authorizedRedirect = "/eatfood/foodlist";
    
    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE, ElementType.METHOD } )
    public @interface Authorized {
	String redirect() default "";
    }   
    /**
     * Registration and Authorization states serv for non-authorized client.
     * Else client accessed to FoodList, DoseList and Logout.
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	boolean authorized = request.getSession(false) == null ? false : request.getSession(false).getAttribute("client") != null;
	if (handler instanceof HandlerMethod) {
	    Method handlerMethod = ((HandlerMethod) handler).getMethod();
	    Authorized annotation = getAuthorizedAnnotation(handlerMethod);
	    if (annotation != null && authorized) {
		return true;
	    }
	    if (annotation != null && !authorized) {
		String redirect = annotation.redirect();
		response.sendRedirect(redirect != null && redirect.length() > 0 ? redirect : defaultRedirect);
		return false;
	    }
	    if (annotation == null && authorized) {
		response.sendRedirect(authorizedRedirect);
		return false;
	    }
	}
	return true;
    }

    private Authorized getAuthorizedAnnotation(Method method) {
	if (method.isAnnotationPresent(Authorized.class)) {
	    return method.getAnnotation(Authorized.class);
	}
	Class<?> methodClass = method.getDeclaringClass();
	if (methodClass.isAnnotationPresent(Authorized.class)) {
	    return methodClass.getAnnotation(Authorized.class);
	}
	return null;
    }
}
