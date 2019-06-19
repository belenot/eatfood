package com.belenot.eatfood.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
	String uri = request.getRequestURI().split("/")[1];
	HttpSession session = request.getSession(false);
	if (uri.equals("foodlist") && session == null) {
	    return false;
	}
	
	return true;
    }
}
