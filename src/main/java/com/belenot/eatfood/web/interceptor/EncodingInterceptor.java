package com.belenot.eatfood.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class EncodingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=utf-8");
	request.setCharacterEncoding("UTF-8");
	return true;
    }
}
    
