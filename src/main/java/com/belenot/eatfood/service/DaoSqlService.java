package com.belenot.eatfood.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.dao.AccountDaoSql;
import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.FoodCriteriaSql;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.dao.FoodListDaoSql;
import com.belenot.eatfood.domain.Account;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.FoodList;

public class DaoSqlService implements DaoService<FoodCriteriaSql> {
    private ClientDaoSql clientDao;
    private FoodDaoSql foodDao;
    private AccountDaoSql accountDao;
    private FoodListDaoSql foodListDao;
    public void setClientDao(ClientDaoSql clientDao) { this.clientDao = clientDao; }
    public void setFoodDao(FoodDaoSql foodDao) { this.foodDao = foodDao; }
    public void setAccountDao(AccountDaoSql accountDao) { this.accountDao = accountDao; }
    public void setFodListDao(FoodListDaoSql foodListDao) { this.foodListDao = foodListDao; }

    @Override
    public Client deleteClient(Client client) throws Exception { return clientDao.deleteClient(client); }
    @Override
    public Client getClientByAccount(Account account) throws Exception { return clientDao.getClientByAccount(account); }
    @Override
    public Client getClientById(int id) throws Exception { return clientDao.getClientById(id); }
    @Override
    public Client newClient(Client client, Account account) throws Exception { return clientDao.newClient(client, account); }
    @Override
    public Client updateClient(Client client) throws Exception { return clientDao.updateClient(client); }
    @Override
    public Food deleteFood(Food food) throws Exception { return foodDao.deleteFood(food); }
    @Override
    public List<Food> getFoodByCriteria(FoodCriteriaSql foodCriteria) throws Exception { return foodDao.getFoodByCriteria(foodCriteria); }
    @Override
    public Food getFoodById(int id) throws Exception { return foodDao.getFoodById(id); }
    @Override
    public Food newFood(Food food) throws Exception { return foodDao.newFood(food); }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception { return foodDao.totalNutrients(client); }
    @Override
    public Food updateFood(Food food) throws Exception { return foodDao.updateFood(food); }
    @Override
    public List<FoodList> getFoodListAfter(Date day, int offset, int limit) throws Exception { return foodListDao.getFoodListAfter(day, offset, limit); }
    @Override
    public List<FoodList> getFoodListBefore(Date day, int offset, int limit) throws Exception { return foodListDao.getFoodListBefore(day, offset, limit); }
    @Override
    public List<FoodList> getFoodListBetween(Date dayMin, Date dayMax) throws Exception { return foodListDao.getFoodListBetween(dayMin, dayMax); }
    @Override
    public FoodList getFoodListById(int id) throws Exception { return foodListDao.getFoodListById(id); }
    @Override
    public FoodList newFoodList(FoodList foodList) throws Exception { return foodListDao.newFoodList(foodList); }
    @Override
    public FoodList updateFoodList(FoodList foodList) throws Exception { return foodListDao.updateFoodList(foodList); }
    @Override
    public Account getAccount(Account account) throws Exception { return accountDao.getAccount(account); }
    @Override
    public Account getAccountByClient(Client client) throws Exception { return accountDao.getAccountByClient(client); }
    @Override
    public Account newAccount(Account account) throws Exception { return accountDao.newAccount(account); }
    @Override
    public Map<String, BigDecimal> totalNutrients(FoodList foodList) throws Exception { return foodListDao.totalNutrients(foodList); }
}
