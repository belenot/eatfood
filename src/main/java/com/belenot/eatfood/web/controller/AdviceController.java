package com.belenot.eatfood.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class AdviceController {
    @Autowired
    private DaoService daoService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
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
}
