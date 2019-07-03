package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
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
    public String foodlist(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws Exception{
	model.addAttribute("doseList", daoService.getDoseByClient(client, 0, 10, true));
	model.addAttribute("totalNutrients", daoService.totalNutrients(client));
	model.addAttribute("messageSource", messageSource);
	return "foodlist";
    }

    @PostMapping( "/addfood" )
    @ResponseBody
    public String addFood(HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	String name = request.getParameter("name");
	boolean common = new Boolean(request.getParameter("common"));
	Map<String, BigDecimal> nutrientMap = new HashMap<>();
	nutrientMap.put("calories", request.getParameter("calories") != null ? new BigDecimal(request.getParameter("calories")) : new BigDecimal(0));
	nutrientMap.put("protein", request.getParameter("protein") != null ? new BigDecimal(request.getParameter("protein")) : new BigDecimal(0));
	nutrientMap.put("carbohydrate", request.getParameter("carbohydrate") != null ? new BigDecimal(request.getParameter("carbohydrate")) : new BigDecimal(0));
	nutrientMap.put("fat", request.getParameter("fat") != null ? new BigDecimal(request.getParameter("fat")) : new BigDecimal(0));
	Food food = daoService.addFood(name, client, nutrientMap, common);
	//response.sendRedirect("./");
	return food.toString();
    }

    @PostMapping ( "/updatefood" )
    public void updateFood(HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	Food food = daoService.getFoodById(Integer.parseInt(request.getParameter("id")));
	food.setClient(client);
	food.setName(request.getParameter("name"));
	food.setCalories(new BigDecimal(request.getParameter("calories")));
	food.setProtein(new BigDecimal(request.getParameter("protein")));
	food.setCarbohydrate(new BigDecimal(request.getParameter("carbohydrate")));
	food.setFat(new BigDecimal(request.getParameter("fat")));
	daoService.updateFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }

    @PostMapping ( "/deletefood" )
    public void deleteFood(@RequestParam( "id" ) int id, HttpServletResponse response) throws Exception, IOException {
	Food food = daoService.getFoodById(id);
	daoService.deleteFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }
	
    @GetMapping ( "/morefood" )
    public String moreFood(@RequestParam( "last" ) int last, @SessionAttribute( "client" ) Client client, Model model ) throws Exception {
	model.addAttribute("foodRows", daoService.getDoseByClient(client, last, 10, true));
	return "foodrows";
    }
}
