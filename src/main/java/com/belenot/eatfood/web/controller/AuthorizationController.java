package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;
import com.belenot.eatfood.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping( "/authorization" )
public class AuthorizationController {

    @Autowired
    private DaoService daoService;
    
    @GetMapping
    public String authorization() {
	return "authorization";
    }

    @PostMapping
    @ResponseBody
    public String authorization(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	Client client = daoService.getClientByLogin(login, password);
	if (client != null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("client", client);
	    return client.getLogin() + "/" + client.getName();
	    //response.sendRedirect("/eatfood/foodlist");
	} else {
	    //Bad
	    throw new ApplicationException("Such client doesn't exist");
	}
    }
	    
	
}
