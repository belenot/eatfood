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
@RequestMapping( "/registration" )
public class RegistrationController {

    @Autowired
    ClientDao clientDao;
    
    @GetMapping
    public String registration() {
	return "registration";
    }

    @PostMapping
    @ResponseBody
    public String registration(HttpServletRequest request) throws ApplicationException {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	Client client = clientDao.addClient(login, password);
	if (client != null) {
	    HttpSession session = request.getSession();
	    session.setAttribute("client", client);
	    return String.format("client{ id=%d, login=%s }", client.getId(), client.getLogin());
	} else {
	    return null;
	}
    }
}
