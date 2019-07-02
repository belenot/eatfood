package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping( "/authorization" )
public class AuthorizationController {

    @Autowired
    DaoService<?> daoService;
    
    @GetMapping
    public String authorization() {
	return "authorization";
    }

    @PostMapping
    @ResponseBody
    public String authorization(HttpServletRequest request, HttpServletResponse response) throws ApplicationException, IOException, Exception {
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	Account account = new Account();
	account.setLogin(login);
	account.setPassword(password);
	Client client = daoService.getClientByAccount(account);
	return client.toString();
    }
}
