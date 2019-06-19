package com.belenot.eatfood.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	String uri = request.getRequestURI().split("/")[2];
	HttpSession session = request.getSession(false);
	boolean accessed = false;
	try {
	    accessed = session == null ? false : session.getAttribute("client") != null;
	} catch (Exception exc) { accessed = false; }
	if (uri.equals("foodlist") && !accessed) {
	    response.sendRedirect("registration");
	    return false;
	}
	if ((uri.equals("registration") || uri.equals("authorization")) && accessed) {
	    response.sendRedirect("foodlist");
	    return false;
	}
	return true;
    }
}
