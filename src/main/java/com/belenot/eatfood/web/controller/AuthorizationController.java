package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping( "/authorization" )
public class AuthorizationController {

    @Autowired
    private ClientDao clientDao;
    
    @GetMapping
    public String authorization() {
	return "authorization";
    }

    @PostMapping
    public void authorization(HttpServletRequest request, HttpServletResponse response) throws ApplicationException, IOException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	Client client = clientDao.getClientByLogin(login, password);
	if (client != null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("client", client);
	    response.sendRedirect("/eatfood/foodlist");
	} else {
	    //Bad
	    throw new ApplicationException("Such client doesn't exist");
	}
    }
	    
	
}
