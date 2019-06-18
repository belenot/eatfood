package com.belenot.eatfood.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;


@Controller
@RequestMapping( "/index" )
public class FoodListController {
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private FoodDao foodDao;
    
    @GetMapping
    public String getHome() {
	return "index";
    }

    @GetMapping( "/client" )
    @ResponseBody
    public String getClient(@RequestParam( "id" ) int id) throws ApplicationException {
	Client client = clientDao.getClient(id);
	String login = client.getLogin();
        return login;
    }

    @GetMapping( "/client/new" )
    @ResponseBody
    public String addClient(@RequestParam( "login" ) String login) throws ApplicationException {
	Client client = clientDao.addClient(login);
	return String.format( "client{id = %d, login = '%s'}", client.getId(), client.getLogin());
    }

    @GetMapping( "/client/{login}")
    @ResponseBody
    public String getClientByLogin(@PathVariable( "login" ) String login) throws ApplicationException {
        List<Client> clientList = clientDao.getClientByLogin(login);
	return clientList.toString();
    }

    @GetMapping( "/food/new" )
    @ResponseBody
    public String addFood(@RequestParam( "name" ) String name, @RequestParam( "login" ) String login) throws ApplicationException {
	Food food = foodDao.addFood(name, clientDao.getClientByLogin(login).get(0));
	return food.toString();
    }

    @GetMapping( "/client/{login}/food" )
    @ResponseBody
    public String getFoodByClient(@PathVariable( "login" ) String login) throws ApplicationException {
	return foodDao.getFoodByClient(clientDao.getClientByLogin(login).get(0)).get(0).getName();
    }

    @GetMapping( "/food" )
    @ResponseBody
    public String getFood(@RequestParam( "id" ) int id) throws ApplicationException {
	return foodDao.getFood(id).getName();
    }
    
}
