package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
    public String foodlist(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws Exception {
	model.addAttribute("doseList", daoService.getDoseByClient(client, 0, 10, true));
	model.addAttribute("totalNutrients", daoService.totalNutrients(client));
	model.addAttribute("foodList", daoService.getFoodByClient(client, 0, Integer.MAX_VALUE, false));
	return "foodlist";
    }

    @PostMapping( "/addfood" )
    public void addFood(HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	String name = request.getParameter("name");
	boolean common = request.getParameter("common") != null && request.getParameter("common").equals("on");
	Map<String, BigDecimal> nutrientMap = new HashMap<>();
	nutrientMap.put("calories", request.getParameter("calories") != null ? new BigDecimal(request.getParameter("calories")) : new BigDecimal(0));
	nutrientMap.put("protein", request.getParameter("protein") != null ? new BigDecimal(request.getParameter("protein")) : new BigDecimal(0));
	nutrientMap.put("carbohydrate", request.getParameter("carbohydrate") != null ? new BigDecimal(request.getParameter("carbohydrate")) : new BigDecimal(0));
	nutrientMap.put("fat", request.getParameter("fat") != null ? new BigDecimal(request.getParameter("fat")) : new BigDecimal(0));
	daoService.addFood(name, client, nutrientMap, common);
	response.sendRedirect("./");
    }

    @PostMapping( "/adddose" )
    public void addDose(HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	Food food = daoService.getFoodByName(request.getParameter("name"), 0, 1, false).get(0);
	BigDecimal gram = new BigDecimal(request.getParameter("gram"));
	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
	daoService.newDose(food, gram, date);
	response.sendRedirect("./");
    }

    @PostMapping( "/updatefood" )
    public void updateFood(HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	Food food = daoService.getFoodById(Integer.parseInt(request.getParameter("id").trim()));
	food.setClient(client);
	food.setCommon(request.getParameter("common") != null);
	food.setName(request.getParameter("name"));
	food.setCalories(new BigDecimal(request.getParameter("calories")));
	food.setProtein(new BigDecimal(request.getParameter("protein")));
	food.setCarbohydrate(new BigDecimal(request.getParameter("carbohydrate")));
	food.setFat(new BigDecimal(request.getParameter("fat")));
	daoService.updateFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }

    @PostMapping( "/updatedose" )
    public void updateDose(HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	Dose dose = daoService.getDoseById(Integer.parseInt(request.getParameter("id")));
	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
	BigDecimal gram = new BigDecimal(request.getParameter("gram"));
	/*dose.setFood(..);/* can't update food, because its not logical(if food change, than it will be another dose"*/
	dose.setDate(date);
	dose.setGram(gram);
	daoService.updateDose(dose);
	response.sendRedirect("./");
	
    }
    @PostMapping ( "/deletefood" )
    public void deleteFood(@RequestParam( "id" ) int id, HttpServletResponse response) throws Exception, IOException {
	Food food = daoService.getFoodById(id);
	daoService.deleteFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }

    @PostMapping( "/deletedose" )
    public void deleteDose(@RequestParam( "id" ) int id, HttpServletResponse response) throws Exception, IOException {
	Dose dose = daoService.getDoseById(id);
	daoService.deleteDose(dose);
	response.sendRedirect("/eatfood/foodlist");
    }

    @GetMapping( "/foods" )
    @ResponseBody
    public String foods(@RequestParam( "offset" ) int offset, @RequestParam( "count" ) int count, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	List<Food> foods = daoService.getFoodByClient(client, offset, count, true);
	StringBuilder sb = new StringBuilder();
	for (Food food : foods) {
	    sb.append(String.format("<div>%s</div><br>",food.toString()));
	}
	return sb.toString();
    }

    @PostMapping( path = "/doses", produces="application/json" )
    @ResponseBody
    public String doses(@RequestParam( "date" ) Date date, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	List<Dose> doses = daoService.getDoseByClient(client, 0, Integer.MAX_VALUE, true, date);
	List<Map<String, String>> doseList = new ArrayList<>();
	for (Dose dose : doses) {
	    Map<String, String> doseMap = new HashMap<>();
	    doseMap.put("dose-id", "" + dose.getId());
	    doseMap.put("food-id", "" + dose.getFood().getId());
	    doseMap.put("food-name", dose.getFood().getName());
	    doseMap.put("food-calories", dose.getFood().getCalories().setScale(2).toString());
	    doseMap.put("food-protein", dose.getFood().getProtein().setScale(2).toString());
	    doseMap.put("food-carbohydrate", dose.getFood().getCarbohydrate().setScale(2).toString());
	    doseMap.put("food-fat", dose.getFood().getFat().setScale(2).toString());
	    doseMap.put("dose-gram", dose.getGram().setScale(2).toString());
	    doseMap.put("dose-date", dose.getDate().toString());
	    doseList.add(doseMap);
	}
	ObjectMapper mapper = new ObjectMapper();
	return mapper.writeValueAsString(doseList);
    }

    @InitBinder
    public void bindShitDate(WebDataBinder binder) {
	binder.addCustomFormatter(new Formatter<Date>() {
		public Date parse(String str, Locale locale) {
		    try {
			return (new SimpleDateFormat("yyyy-MM-dd", locale)).parse(str);
		    } catch (Exception exc) {
			return null;
		    }
		}
		public String print(Date date, Locale locale) {
		    return (new SimpleDateFormat("yyyy-MM-dd", locale)).format(date);
		}
	    });
		
    }
    
    @GetMapping( "/morefood" )
    public String moreFood(@RequestParam( "last" ) int last, @SessionAttribute( "client" ) Client client, Model model ) throws Exception {
	model.addAttribute("foodRows", daoService.getDoseByClient(client, last, 10, true));
	return "foodrows";
    }
}
