package com.belenot.eatfood.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.AuthorizationService;
import com.belenot.eatfood.web.interceptor.SessionInterceptor.Authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping( "/client" )
public class AuthorozationController {

    @Autowired
    private AuthorizationService authorizationService;
    public void setAuthorizationService(AuthorizationService authorizationService) { this.authorizationService = authorizationService; }
    
    @PostMapping( "/login" )
    @ResponseBody
    public boolean logIn(@RequestParam String login, @RequestParam String password, HttpServletRequest request) {
	Client client = authorizationService.authorizeClient(login, password);
	if (client != null) {
	    request.getSession().setAttribute("client", client);
	    return true;
	}
	return false;
    }
    
    @PostMapping( "/logout" )
    @ResponseBody
    @Authorized
    public boolean logOut(@SessionAttribute Client client, HttpSession httpSession) {
	if (client == null || !authorizationService.isValidClient(client)) return false;
	httpSession.removeAttribute("client");
	return true;
    }
}
    