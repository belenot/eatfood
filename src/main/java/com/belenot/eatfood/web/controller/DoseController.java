package com.belenot.eatfood.web.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DoseService;
import com.belenot.eatfood.web.interceptor.SessionInterceptor.Authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping( path = "/dose" )
@Authorized
public class DoseController {
    @Autowired
    private DoseService doseService;
    public void setDoseService(DoseService doseService) {
	this.doseService = doseService;
    }

    @GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public Dose getDose(@PathVariable int id) {
	return doseService.getDoseById(id);
    }

    @RequestMapping( path = "/byfood", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public List<Dose> getDose(@RequestBody Food food) {
        return doseService.getDoseByFood(food);	
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public List<Dose> getDose(@SessionAttribute( "client" ) Client client) {
	return doseService.getDoseByClient(client);
    }


    @PostMapping( path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Dose addDose(@RequestBody Dose dose) {
	Food food = dose.getFood();
	dose.setFood(null);
	doseService.addDose(food, dose);
	return doseService.getDoseById(dose.getId());
    }

    // I think there is no great reason to request database to copmute date interval
    @GetMapping( params = { "dateFirst", "dateLast" } )
    @ResponseBody
    public List<Dose> getDose(@RequestParam Date dateFirst, @RequestParam Date dateLast, @SessionAttribute Client client) {
	return getDose(client).stream().filter( d -> (d.getDate().compareTo(dateFirst) + d.getDate().compareTo(dateLast) == 0)).collect(Collectors.toList());
    }
    
	
    @GetMapping( path = "/delete/{id}" )
    @ResponseBody
    public boolean deleteDose(@PathVariable int id) {
	Dose dose = doseService.getDoseById(id);
	doseService.deleteDose(dose);
        dose = doseService.getDoseById(dose.getId());
	return dose == null;
    }
	
		     

}
