package com.belenot.eatfood.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.web.interceptor.SessionInterceptor.Authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping( "/food" )
@Authorized
public class FoodController {

    @Autowired
    private FoodService foodService;
    public void setFoodService(FoodService foodService) {
	this.foodService = foodService;
    }
    
    @GetMapping( path = "/{id}",
		     produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Food getFood(@PathVariable int id) {
	return foodService.getFoodById(id);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public List<Food> getFood(@SessionAttribute( "client" ) Client client) {
	List<Food> foods = new ArrayList<>(foodService.getFoodByClient(client));
	return foods;
    }

    @PostMapping( path = "/add",
		  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Food addFood(@RequestBody Food food, @SessionAttribute( "client" ) Client client) {
	foodService.addFood(client, food);
        food = foodService.getFoodById(food.getId());
	return food;
    }

    @PostMapping( path = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Food updateFood(@SessionAttribute("client") Client client, @RequestBody Food food) {
	
	foodService.updateFood(food.setClient(client));
	return foodService.getFoodById(food.getId());
    }

    @GetMapping( path = "/delete/{id}",
		  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public boolean deleteFood(@PathVariable int id) {
	Food food = foodService.getFoodById(id);
	foodService.deleteFood(food);
	food = foodService.getFoodById(id);
	return food == null;
    }
    
}



