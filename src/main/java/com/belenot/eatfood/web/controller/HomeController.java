package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.web.form.SignUpForm;
import com.belenot.eatfood.web.model.ClientModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

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