package com.belenot.eatfood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping
    public String eatfood() {
        return "eatfood";
    }
}