package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DaoService;
import com.belenot.eatfood.web.model.FoodModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping( "/foodlist" )
public class FoodListController {
    @Autowired
    private DaoService daoService;
    @Autowired
    private MessageSource messageSource;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectWriter objectWriter = objectMapper.writer();
    
    @GetMapping
    public String foodlist(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws Exception {
	model.addAttribute("doseList", daoService.getDoseByClient(client, 0, 10, true));
	model.addAttribute("totalNutrients", daoService.totalNutrients(client));
	model.addAttribute("foodList", daoService.getFoodByClient(client, 0, Integer.MAX_VALUE, false));
	return "foodlist";
    }

    @PostMapping( value = "/addfood", produces="application/json; charset=utf-8" )
    @ResponseBody
    public Food addFood(Food food, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	food.setClient(client);
	food = daoService.addFood(food);
	return food;
    }


    @PostMapping( value = "/updatefood", produces = "application/json; charset=utf-8" )
    @ResponseBody
    public Food updateFood(Food food, HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	food.setClient(client);
        daoService.updateFood(food);
	food = daoService.getFoodById(food.getId());
	return food;
    }
    
    @PostMapping ( "/deletefood" )
    @ResponseBody
    public String deleteFood(Food food, HttpServletResponse response) throws Exception, IOException {
	return Boolean.toString(daoService.deleteFood(food));
    }

    @GetMapping( value = "/foods", produces = "application/json; charset=utf-8" )
    @ResponseBody
    public List<Food> foods(@RequestParam( value="offset", required=false) Integer offset, @RequestParam( value="count", required=false ) Integer count, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	offset = offset == null ? 0 : offset;
	count = count == null ? Integer.MAX_VALUE : count;
	List<Food> foods = daoService.getFoodByClient(client, offset, count, true);
	List<FoodModel> foodList = new ArrayList<>();
	for (Food food : foods) {
	    foodList.add(new FoodModel(food));
	}
	return foods;

    }
}
