package com.belenot.eatfood.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

public class DaoServiceDefault implements DaoService {

    @Override
    public Client addClient(Client client) throws Exception {
	return null;
    }

    @Override
    public boolean deleteClient(Client client) throws Exception {
	return false;
    }

    @Override
    public Client getClientById(int id) throws Exception {
	return null;
    }

    @Override
    public Client getClientByLogin(String login, String password) throws Exception {
	return null;
    }

    @Override
    public void updateClient(Client client) throws Exception {
		
    }

    @Override
    public Food addFood(Food food) throws Exception {
	return null;
    }

    @Override
    public boolean deleteFood(Food food) throws Exception {
	return false;
    }

    @Override
    public List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception {
	return null;
    }

    @Override
    public Food getFoodById(int id) throws Exception {
	return null;
    }

    @Override
    public List<Food> getFoodByName(Food food, int start, int count, boolean desc) throws Exception {
	return null;
    }

    @Override
    public void updateFood(Food food) throws Exception {
		
    }

    @Override
    public boolean deleteDose(Dose dose) throws Exception {
	return false;
    }

    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc) throws Exception {
	return null;
    }

    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc, Date dateFirst, Date dateLast)
	throws Exception {
	return null;
    }

    @Override
    public List<Dose> getDoseByFood(Food food, int offset, int limit, boolean desc) throws Exception {
	return null;
    }

    @Override
    public Dose getDoseById(int id) throws Exception {
	return null;
    }

    @Override
    public Dose newDose(Dose dose) throws Exception {
	return null;
    }

    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception {
	return null;
    }

    @Override
    public void updateDose(Dose dose) throws Exception {
		
    }

}
