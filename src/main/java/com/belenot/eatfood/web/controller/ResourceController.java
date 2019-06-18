package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


@Controller
@RequestMapping( "/**/*.{js,css}" )
public class ResourceController {
    /**
     * example: /eatfood/index/index.css -> WEB-INF/resources/index/index.css
     * eatfood - servlet namespace
     * resources - any ordinary folder which is responsabled to hold such files like css or js
     */
    @RequestMapping
    @ResponseBody
    public String getSource(HttpServletRequest request) throws IOException, ServletException {
	ServletContext ctx = request.getServletContext();
	String[] resourceURIPathArray = request.getRequestURI().split("/");
	resourceURIPathArray[0] = "WEB-INF";
	resourceURIPathArray[1] = "resources";
	String resourceURI = String.join("/", resourceURIPathArray);
        InputStream in = ctx.getResourceAsStream(resourceURI);
	if (in == null) {
	    throw new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), null);
	}
	int c;
	String response = "";
	while( (c = in.read()) > 0 && in.available() > 0 ) {
	    response += (char) c;
	}
	return response;
    }
}
