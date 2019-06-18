package com.belenot.eatfood.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.domain.Client;


@Controller
@RequestMapping( "/index" )
public class FoodListController {
    @Autowired
    private ClientDao clientDao;
    
    @GetMapping
    public String getHome() {
	return "index";
    }

    @GetMapping( "/client/{id}" )
    @ResponseBody
    public String getClient(@PathVariable( "id" ) int id) {
	Client client = clientDao.getClient(id);
	String login = client.getLogin();
        return login;
    }
	
    
}
