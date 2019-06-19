package com.belenot.eatfood.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;


@Controller
@RequestMapping( "/foodlist" )
public class FoodListController {
    @Autowired
    private FoodDao foodDao;
    
    @GetMapping
    @ResponseBody
    public String getHome(HttpServletRequest request, @SessionAttribute( "client" ) Client client) {
	return String.format("Allowed food list for client { id = %d, login = %s }", client.getId(), client.getLogin());
    }

    
}
