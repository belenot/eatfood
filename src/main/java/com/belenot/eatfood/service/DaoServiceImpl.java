package com.belenot.eatfood.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.dao.DoseDao;
import com.belenot.eatfood.dao.FoodDao;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

public class DaoServiceImpl implements DaoService {
    private ClientDao clientDao;
    private FoodDao foodDao;
    private DoseDao doseDao;


    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }
    public void setFoodDao(FoodDao foodDao) { this.foodDao = foodDao; }
    public void setDoseDao(DoseDao doseDao) { this.doseDao = doseDao; }


    @Override
    public Client addClient(Client client) throws Exception {
	return clientDao.addClient(client);
    }
    @Override
    public boolean deleteClient(Client client) throws Exception {
	return clientDao.deleteClient(client);
    }
    @Override
    public Client getClientById(int id) throws Exception {
	return clientDao.getClientById(id);
    }
    @Override
    public Client getClientByLogin(String login, String password) throws Exception {
	return clientDao.getClientByLogin(login, password);
    }
    @Override
    public void updateClient(Client client) throws Exception {
	clientDao.updateClient(client);
    }
    @Override
    public Food addFood(Food food)
	throws Exception {
	return foodDao.addFood(food);
    }
    @Override
    public boolean deleteFood(Food food) throws Exception {
	return foodDao.deleteFood(food);
    }
    @Override
    public List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception {
	return foodDao.getFoodByClient(client, start, count, desc);
    }
    @Override
    public Food getFoodById(int id) throws Exception {
	return foodDao.getFoodById(id);
    }
    @Override
    public List<Food> getFoodByName(String name, int start, int count, boolean desc) throws Exception {
	return foodDao.getFoodByName(name, start, count, desc);
    }
    @Override
    public void updateFood(Food food) throws Exception {
	foodDao.updateFood(food);
    }
    @Override
    public void deleteDose(Dose dose) throws Exception {
	doseDao.deleteDose(dose);
    }
    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc) throws Exception {
	return doseDao.getDoseByClient(client, offset, limit, desc);
    }
    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc, Date date) throws Exception {
	return doseDao.getDoseByClient(client, offset, limit, desc, date);
    }
    @Override
    public List<Dose> getDoseByFood(Food food, int offset, int limit, boolean desc) throws Exception {
	return doseDao.getDoseByFood(food, offset, limit, desc);
    }
    @Override
    public Dose getDoseById(int id) throws Exception {
	return doseDao.getDoseById(id);
    }
    @Override
    public Dose newDose(Dose dose) throws Exception {
	return doseDao.newDose(dose);
    }
    @Override
    public void updateDose(Dose dose) throws Exception {
	doseDao.updateDose(dose);
    }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception{
	return doseDao.totalNutrients(client);
    }

}
