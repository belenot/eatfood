package com.belenot.eatfood.web.controller;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DoseService;
import com.belenot.eatfood.web.interceptor.SessionInterceptor.Authorized;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping( path = "/dose" )
@Authorized
public class DoseController {
    private DoseService doseService;
    public void setDoseService(DoseService doseService) {
	this.doseService = doseService;
    }

    @RequestMapping( path = "/${id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public Dose getDose(@PathVariable int id) {
	return doseService.getDoseById(id);
    }

    @RequestMapping( path = "/byfood", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public List<Dose> getDose(@RequestBody Food food) {
        return doseService.getDoseByFood(food);
	
    }

    @RequestMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public List<Dose> getDose(@SessionAttribute Client client) {
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
	
	
    @RequestMapping( path = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public boolean deleteDose(@RequestBody Dose dose) {
	doseService.deleteDose(dose);
        dose = doseService.getDoseById(dose.getId());
	return dose == null;
    }
	
		     

}
