package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping( "/foodlist" )
public class FoodListController {
    @Autowired
    private FoodDao foodDao;
    
    @GetMapping
    public String getHome(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws ApplicationException{
	model.addAttribute("foodRows", foodDao.getFoodByClientLast(client, 0, 10));
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

    @PostMapping ( "/updatefood" )
    public void updateFood(HttpServletRequest request, @SessionAttribute( "client" ) Client client) throws ApplicationException, IOException {
	Food food = foodDao.getFood(Integer.parseInt(request.getParameter("id")));
	food.setClient(client);
	food.setName(request.getParameter("name"));
	food.setCalories(new BigDecimal(request.getParameter("calories")));
	food.setProtein(new BigDecimal(request.getParameter("protein")));
	food.setCarbohydrate(new BigDecimal(request.getParameter("carbohydrate")));
	food.setFat(new BigDecimal(request.getParameter("fat")));
	foodDao.updateFood(food);
    }

    @PostMapping ( "/deletefood" )
    public void deleteFood(@RequestParam( "id" ) int id, HttpServletResponse response) throws ApplicationException, IOException {
	Food food = foodDao.getFood(id);
	foodDao.deleteFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }
	
    @GetMapping ( "/morefood" )
    public String moreFood(@RequestParam( "last" ) int last, @SessionAttribute( "client" ) Client client, Model model ) throws ApplicationException {
	model.addAttribute("foodRows", foodDao.getFoodByClientLast(client, last, 10));
	return "foodrows";
    }
}
