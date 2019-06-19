package com.belenot.eatfood.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;


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
    @ResponseBody
    public String authorization(HttpServletRequest request) throws ApplicationException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	Client client = clientDao.getClientByLogin(login, password);
	if (client != null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("client", client);
	    return String.format("Authorization allow for user { id = %d, login = %s }", client.getId(), client.getLogin());
	}
	return "Authorization not allowed";
    }
	    
	
}
