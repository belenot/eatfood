package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.domain.Account;
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
@RequestMapping( "/registration" )
public class RegistrationController {

    @Autowired
    DaoService<?> daoService;
    
    @GetMapping
    public String registration() {
	return "registration";
    }

    @PostMapping
    @ResponseBody
    public String registration(HttpServletRequest request, HttpServletResponse response) throws ApplicationException, IOException, Exception {
	//Error: no check for unique
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String surname = request.getParameter("surname");
	String email = request.getParameter("email");
	Account account = new Account();
	account.setLogin(login);
	account.setPassword(password);
	Client client = new Client();
	client.setName(name);
	client.setSurname(surname);
	client.setEmail(email);
        client = daoService.newClient(client, account);
	if (client != null) {
	    //HttpSession session = request.getSession();
	    //session.setAttribute("client", client);
	    //response.sendRedirect("/eatfood/foodlist");
	    return client.toString();
	}
	return null;
    }
}
