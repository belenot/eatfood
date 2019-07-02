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
import com.belenot.eatfood.exception.ApplicationException;

public class DaoSqlService implements DaoService<FoodCriteriaSql> {
    private ClientDaoSql clientDaoSql;
    private FoodDaoSql foodDaoSql;
    private AccountDaoSql accountDaoSql;
    private FoodListDaoSql foodListDaoSql;
    public void setClientDaoSql(ClientDaoSql clientDaoSql) { this.clientDaoSql = clientDaoSql; }
    public void setFoodDaoSql(FoodDaoSql foodDaoSql) { this.foodDaoSql = foodDaoSql; }
    public void setAccountDaoSql(AccountDaoSql accountDaoSql) { this.accountDaoSql = accountDaoSql; }
    public void setFodListDaoSql(FoodListDaoSql foodListDaoSql) { this.foodListDaoSql = foodListDaoSql; }

    @Override
    public Client deleteClient(Client client) throws ApplicationException {
	try {
	    return clientDaoSql.deleteClient(client);
	} catch (Exception exc) {
	    throw new ApplicationException("", exc);
	}
    }
    @Override
    public Client getClientByAccount(Account account) throws Exception { return clientDaoSql.getClientByAccount(account); }
    @Override
    public Client getClientById(int id) throws Exception { return clientDaoSql.getClientById(id); }
    @Override
    public Client newClient(Client client, Account account) throws Exception { return clientDaoSql.newClient(client, account); }
    @Override
    public Client updateClient(Client client) throws Exception { return clientDaoSql.updateClient(client); }
    @Override
    public Food deleteFood(Food food) throws Exception { return foodDaoSql.deleteFood(food); }
    @Override
    public List<Food> getFoodByCriteria(FoodCriteriaSql foodCriteria) throws Exception { return foodDaoSql.getFoodByCriteria(foodCriteria); }
    @Override
    public Food getFoodById(int id) throws Exception { return foodDaoSql.getFoodById(id); }
    @Override
    public Food newFood(Food food) throws Exception { return foodDaoSql.newFood(food); }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception { return foodDaoSql.totalNutrients(client); }
    @Override
    public Food updateFood(Food food) throws Exception { return foodDaoSql.updateFood(food); }
    @Override
    public List<FoodList> getFoodListAfter(Date day, int offset, int limit) throws Exception { return foodListDaoSql.getFoodListAfter(day, offset, limit); }
    @Override
    public List<FoodList> getFoodListBefore(Date day, int offset, int limit) throws Exception { return foodListDaoSql.getFoodListBefore(day, offset, limit); }
    @Override
    public List<FoodList> getFoodListBetween(Date dayMin, Date dayMax) throws Exception { return foodListDaoSql.getFoodListBetween(dayMin, dayMax); }
    @Override
    public FoodList getFoodListById(int id) throws Exception { return foodListDaoSql.getFoodListById(id); }
    @Override
    public FoodList newFoodList(FoodList foodList) throws Exception { return foodListDaoSql.newFoodList(foodList); }
    @Override
    public FoodList updateFoodList(FoodList foodList) throws Exception { return foodListDaoSql.updateFoodList(foodList); }
    @Override
    public Account getAccount(Account account) throws Exception { return accountDaoSql.getAccount(account); }
    @Override
    public Account getAccountByClient(Client client) throws Exception { return accountDaoSql.getAccountByClient(client); }
    @Override
    public Account newAccount(Account account) throws Exception { return accountDaoSql.newAccount(account); }
    @Override
    public Map<String, BigDecimal> totalNutrients(FoodList foodList) throws Exception { return foodListDaoSql.totalNutrients(foodList); }
}
