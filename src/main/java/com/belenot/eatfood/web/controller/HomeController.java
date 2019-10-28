package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HomeController {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ClientService clientService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/eatfood")
    public String eatfoodPage() {
        return "eatfood";
    }
}