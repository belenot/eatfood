package com.belenot.eatfood.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;


@Controller
@RequestMapping( "/foodlist" )
public class FoodListController {
    @Autowired
    private FoodDao foodDao;
    
    @GetMapping
    @ResponseBody
    public String getHome(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    Client client = (Client) session.getAttribute("client");
	    return String.format("Allowed food list for client { id = %d, login = %s }", client.getId(), client.getLogin());
	} else {
	    return null;
	}
    }

    
}
