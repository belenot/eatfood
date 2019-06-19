package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;


@Controller
@RequestMapping( "/foodlist" )
public class FoodListController {
    @Autowired
    private FoodDao foodDao;
    
    @GetMapping
    public String getHome(HttpServletRequest request, @SessionAttribute( "client" ) Client client) throws ApplicationException{
	String responseString = "";
	for (Food food : foodDao.getFoodByClient(client, 0, 10)) {
	    responseString += String.format("%s|%.2f|%.2f|%.2f|%.2f\n<br>", food.getName(), food.getCalories(), food.getProtein(), food.getCarbohydrate(), food.getFat());
	}
	return "foodlist";
    }

    @PostMapping( "/addfood" )
    public void addFood(HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws ApplicationException, IOException {
	String name = request.getParameter("name");
	Map<String, BigDecimal> nutrientMap = new HashMap<>();
	nutrientMap.put("calories", request.getParameter("calories") != null ? new BigDecimal(request.getParameter("calories")) : new BigDecimal(0));
	nutrientMap.put("protein", request.getParameter("protein") != null ? new BigDecimal(request.getParameter("protein")) : new BigDecimal(0));
	nutrientMap.put("carbohydrate", request.getParameter("carbohydrate") != null ? new BigDecimal(request.getParameter("carbohydrate")) : new BigDecimal(0));
	nutrientMap.put("fat", request.getParameter("fat") != null ? new BigDecimal(request.getParameter("fat")) : new BigDecimal(0));
	Food food = foodDao.addFood(name, client, nutrientMap);
	response.sendRedirect("./");
    }

    
}
