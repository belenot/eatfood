package com.belenot.eatfood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller( "/index" )
public class FoodListController {
    @GetMapping
    public String getHome() {
	return "index";
    }
    
}
