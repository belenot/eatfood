package com.belenot.eatfood.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping( "/doselist" )
public class DoseListController {
    @Autowired
    private DaoService daoService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String doseList(ModelMap modelMap) {
	Date today = new Date();
	modelMap.addAttribute("dateFirst", (new SimpleDateFormat("yyyy-MM-dd")).format(today));
	modelMap.addAttribute("dateLast", (new SimpleDateFormat("yyyy-MM-dd")).format(today));
	return "doselist";
    }

    @PostMapping( value = "/adddose", produces="application/json; charset=utf-8" )
    @ResponseBody
    public Dose addDose(Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	dose.getFood().setClient(client);
	Food food = daoService.getFoodByName(dose.getFood(), 0, 1, false).get(0);
	dose.setFood(food);
        dose = daoService.newDose(dose);
	return dose;
    }
    
    @PostMapping( value = "/updatedose", produces="application/json; charset=utf-8", consumes = "application/json; charset=utf-8" )
    @ResponseBody
    public Dose updateDose(@RequestBody Dose dose, HttpServletRequest request, HttpServletResponse response, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	/*dose.setFood(..);/* can't update food, because its not logical(if food change, than it would be another dose"*/
	daoService.updateDose(dose);
	dose = daoService.getDoseById(dose.getId());
	return dose;
    }

    @PostMapping( "/deletedose" )
    @ResponseBody
    public String deleteDose(Dose dose, HttpServletResponse response) throws Exception, IOException {
	return Boolean.toString(daoService.deleteDose(dose));
    }

    @PostMapping( value = "/doses", produces="application/json" )
    @ResponseBody
    public List<Dose> doses(@RequestParam( "dateFirst" ) Date dateFirst, @RequestParam( "dateLast" ) Date dateLast, @SessionAttribute( "client" ) Client client) throws Exception, IOException {
	List<Dose> doses = daoService.getDoseByClient(client, 0, Integer.MAX_VALUE, true, dateFirst, dateLast);
	return doses;
    }
}
