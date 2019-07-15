package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping
    public String foodlist(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws Exception {
	model.addAttribute("doseList", daoService.getDoseByClient(client, 0, 10, true));
	model.addAttribute("totalNutrients", daoService.totalNutrients(client));
	model.addAttribute("foodList", daoService.getFoodByClient(client, 0, Integer.MAX_VALUE, false));
	return "foodlist";
    }

    @PostMapping( value = "/addfood", produces="application/json; charset=utf-8", consumes = "application/json; charset=utf-8" )
    @ResponseBody
    public Food addFood(@RequestBody Food food, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	food.setClient(client);
	food = daoService.addFood(food);
	return food;
    }


    @PostMapping( value = "/updatefood", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8" )
    @ResponseBody
    public Food updateFood(@RequestBody Food food, HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	food.setClient(client);
        daoService.updateFood(food);
	food = daoService.getFoodById(food.getId());
	return food;
    }
    
    @PostMapping ( value="/deletefood", consumes="application/json", produces="application/json" )
    @ResponseBody
    public Map<String, Boolean> deleteFood(@RequestBody Food food, HttpServletResponse response) throws Exception, IOException {
	Map<String, Boolean> result = new HashMap<>();
	result.put("result", daoService.deleteFood(food));
	return result;
    }

    @PostMapping( value = "/foods", produces = "application/json; charset=utf-8", consumes = "application/json" )
    @ResponseBody
    public List<Food> foods(@RequestBody Map<String, String> opt, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	int offset = 0;
	int count = Integer.MAX_VALUE;
	try {
	    offset = Integer.parseInt(opt.get("offset"));
	} catch (Exception exc) { };
	try {
	    count = Integer.parseInt(opt.get("count"));
	} catch (Exception exc) { };
	List<Food> foods = daoService.getFoodByClient(client, offset, count, true);
	return foods;

    }
}
