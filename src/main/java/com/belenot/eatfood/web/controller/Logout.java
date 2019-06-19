package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping( "/logout" )
public class Logout {

    @GetMapping
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession(false);
	session.invalidate();
	response.sendRedirect("authorization");
    }
	
}
