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
import com.belenot.eatfood.web.model.DoseModel;
import com.belenot.eatfood.web.model.FoodModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectWriter objectWriter = objectMapper.writer();
    
    @GetMapping
    public String foodlist(HttpServletRequest request, @SessionAttribute( "client" ) Client client, Model model) throws Exception {
	model.addAttribute("doseList", daoService.getDoseByClient(client, 0, 10, true));
	model.addAttribute("totalNutrients", daoService.totalNutrients(client));
	model.addAttribute("foodList", daoService.getFoodByClient(client, 0, Integer.MAX_VALUE, false));
	return "foodlist";
    }

    @PostMapping( "/addfood" )
    @ResponseBody
    public String addFood(Food food, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	food.setClient(client);
	food = daoService.addFood(food);
	return objectWriter.writeValueAsString(new FoodModel(food));
    }

    @PostMapping( "/adddose" )
    @ResponseBody
    public String addDose(Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	dose.getFood().setClient(client);
	Food food = daoService.getFoodByName(dose.getFood(), 0, 1, false).get(0);
	dose.setFood(food);
        dose = daoService.newDose(dose);
	return objectWriter.writeValueAsString(new DoseModel(dose));
    }

    @PostMapping( "/updatefood" )
    @ResponseBody
    public String updateFood(Food food, HttpServletRequest request, @SessionAttribute( "client" ) Client client, HttpServletResponse response) throws Exception, IOException {
	food.setClient(client);
        daoService.updateFood(food);
	food = daoService.getFoodById(food.getId());
	return objectWriter.writeValueAsString(new FoodModel(food));
    }

    @PostMapping( "/updatedose" )
    @ResponseBody
    public String updateDose(Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	/*dose.setFood(..);/* can't update food, because its not logical(if food change, than it would be another dose"*/
	daoService.updateDose(dose);
	dose = daoService.getDoseById(dose.getId());
	return objectWriter.writeValueAsString(new DoseModel(dose));
    }
    
    @PostMapping ( "/deletefood" )
    @ResponseBody
    public String deleteFood(Food food, HttpServletResponse response) throws Exception, IOException {
	return Boolean.toString(daoService.deleteFood(food));
    }

    @PostMapping( "/deletedose" )
    @ResponseBody
    public String deleteDose(Dose dose, HttpServletResponse response) throws Exception, IOException {
	return Boolean.toString(daoService.deleteDose(dose));
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
	List<DoseModel> doseList = new ArrayList<>();
	for (Dose dose : doses) {
	    doseList.add(new DoseModel(dose));
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
