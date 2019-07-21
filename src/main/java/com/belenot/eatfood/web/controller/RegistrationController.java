package com.belenot.eatfood.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping( "/registration" )
public class RegistrationController {

    @Autowired
    DaoService daoService;
    
    @GetMapping
    public String registration() {
	return "registration";
    }

    @PostMapping
    public void registration(Client client, HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
	client = daoService.addClient(client);
    }
}
