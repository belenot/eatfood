package com.belenot.eatfood.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    /**
     * Registration and Authorization states serv for non-authorized client.
     * Else client accessed to FoodList, DoseList and Logout.
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	String uri = request.getRequestURI().split("/")[2];
	HttpSession session = request.getSession(false);
	boolean accessed = false;
	try {
	    accessed = session == null ? false : session.getAttribute("client") != null;
	} catch (Exception exc) { accessed = false; }
	if ((uri.equals("foodlist") || uri.equals("doselist")) && !accessed) {
	    response.sendRedirect("/eatfood/registration");
	    return false;
	}
	if ((uri.equals("registration") || uri.equals("authorization")) && accessed) {
	    response.sendRedirect("/eatfood/doselist");
	    return false;
	}
	if (uri.equals("logout") && !accessed) {
	    response.sendRedirect("/eatfood/authorization");
	    return false;
	}
	return true;
    }
}
