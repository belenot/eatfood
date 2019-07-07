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
    public void addFood(Food food, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	Map<String, BigDecimal> nutrientMap = new HashMap<>();
	food.setClient(client);
	daoService.addFood(food);
	response.sendRedirect("./");
    }

    @PostMapping( "/adddose" )
    public void addDose(Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	Food food = daoService.getFoodByName(request.getParameter("name"), 0, 1, false).get(0);
	dose.setFood(food);
	daoService.newDose(dose);
	response.sendRedirect("./");
    }

    @PostMapping( "/updatefood" )
    public void updateFood(Food food, HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	food.setClient(client);
	daoService.updateFood(food);
	response.sendRedirect("/eatfood/foodlist");
    }

    @PostMapping( "/updatedose" )
    public void updateDose(Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	/*dose.setFood(..);/* can't update food, because its not logical(if food change, than it will be another dose"*/
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
	binder.addCustomFormatter(new Formatter<Client>() {
		@Override
		public Client parse(String id, Locale locale) { try { return daoService.getClientById(Integer.parseInt(id)); } catch (Exception exc) { return null; } }
		@Override
		public String print(Client client, Locale locale) { return client.toString(); }
	    });
	
    }
    
    @GetMapping( "/morefood" )
    public String moreFood(@RequestParam( "last" ) int last, @SessionAttribute( "client" ) Client client, Model model ) throws Exception {
	model.addAttribute("foodRows", daoService.getDoseByClient(client, last, 10, true));
	return "foodrows";
    }
}
