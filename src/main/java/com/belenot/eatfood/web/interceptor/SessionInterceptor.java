package com.belenot.eatfood.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    /**
     * Registration and Authorization states serv for non-authorized client.
     * Else client accessed to FoodList, DoseList and Logout.
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	
	return true;
    }
}
