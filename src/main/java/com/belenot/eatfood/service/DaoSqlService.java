package com.belenot.eatfood.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import org.springframework.beans.factory.annotation.Autowired;

public class DaoSqlService implements DaoService<FoodCriteriaSql> {
    private ClientDaoSql clientDaoSql;
    private FoodDaoSql foodDaoSql;
    private AccountDaoSql accountDaoSql;
    private FoodListDaoSql foodListDaoSql;
    private String connectionAddress;
    private String username;
    private String password;

    private Connection connection;
    
    public void setClientDaoSql(ClientDaoSql clientDaoSql) { this.clientDaoSql = clientDaoSql; }
    public void setFoodDaoSql(FoodDaoSql foodDaoSql) { this.foodDaoSql = foodDaoSql; }
    public void setAccountDaoSql(AccountDaoSql accountDaoSql) { this.accountDaoSql = accountDaoSql; }
    public void setFoodListDaoSql(FoodListDaoSql foodListDaoSql) { this.foodListDaoSql = foodListDaoSql; }

    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public String getConnectionAddress() { return connectionAddress; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public boolean isConnected() {
	try {
	    boolean connected = connection != null && !connection.isClosed();
	    return connected;
	} catch (SQLException exc) {
	    //log
	    return false;
	}
    }

    //Replace on event when context loaded
    public void init() {
	try {
	    connection = DriverManager.getConnection(connectionAddress, username, password);
	    clientDaoSql.setConnection(connection);
	    accountDaoSql.setConnection(connection);
	    foodDaoSql.setConnection(connection);
	    foodListDaoSql.setConnection(connection);
	} catch (SQLException exc) {
	    //log;
	}
    }
    
    @Override
    public void close() {
	try {
	    connection.close();
	} catch (SQLException exc) {
	    //log
	}
    }

    @Override
    public Client deleteClient(Client client) throws ApplicationException {
	try {
	    return clientDaoSql.deleteClient(client);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't delete client from ClientDaoSql:\n" + client.toString() + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Client getClientByAccount(Account account) throws ApplicationException {
	try {
	    return clientDaoSql.getClientByAccount(account);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get client by account from ClientDaoSql:\n" + account.toString() + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Client getClientById(int id) throws ApplicationException {
	try {
	    return clientDaoSql.getClientById(id);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get client by id = "+id+" from ClientDaoSql:\n" + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Client newClient(Client client, Account account) throws ApplicationException {
	try{
	    return clientDaoSql.newClient(client, account);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't create client with account in ClientDaoSql:\n" + client.toString() + account.toString() + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Client updateClient(Client client) throws ApplicationException {
	try {
	    return clientDaoSql.updateClient(client);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't update client in ClientDaoSql:\n" + client.toString() + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Food deleteFood(Food food) throws ApplicationException {
	try {
	    return foodDaoSql.deleteFood(food);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't delete food from FoodDaoSql:\n" + food.toString() + foodDaoSql.toString(), exc);
	}
    }
    @Override
    public List<Food> getFoodByCriteria(FoodCriteriaSql foodCriteria) throws ApplicationException {
	try {
	    return foodDaoSql.getFoodByCriteria(foodCriteria);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get food by criteria("+foodCriteria.criteria()+") from FoodDaoSql:\n" + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Food getFoodById(int id) throws ApplicationException {
	try {
	    return foodDaoSql.getFoodById(id);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get food by id = "+id+" from ClientDaoSql:\n" + clientDaoSql.toString(), exc);
	}
    }	    
    @Override
    public Food newFood(Food food) throws ApplicationException {
	try {
	    return foodDaoSql.newFood(food);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't create food in ClientDaoSql:\n" + food.toString() + clientDaoSql.toString(), exc);
	}
    }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws ApplicationException {
	try {
	    return foodDaoSql.totalNutrients(client);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get total nutrients for client from FoodDaoSql:\n" + client.toString() + foodDaoSql.toString(), exc);
	}
    }
    @Override
    public Food updateFood(Food food) throws ApplicationException {
	try {
	    return foodDaoSql.updateFood(food);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't update food in FoodDaoSql:\n" + food.toString() + foodDaoSql.toString(), exc);
	}
    }
    @Override
    public List<FoodList> getFoodListAfter(Date day, int offset, int limit) throws ApplicationException {
	try {
	    return foodListDaoSql.getFoodListAfter(day, offset, limit);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get foodList after "+day.toString()+"("+offset+", "+limit+") from FoodListDaoSql:\n" + foodListDaoSql.toString(), exc);
	}
    }	    
    @Override
    public List<FoodList> getFoodListBefore(Date day, int offset, int limit) throws ApplicationException {
	try {
	    return foodListDaoSql.getFoodListBefore(day, offset, limit);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get foodList before "+day.toString()+"("+offset+", "+limit+") from FoodListDaoSql:\n" + foodListDaoSql.toString(), exc);
	}
    }	    
    @Override
    public List<FoodList> getFoodListBetween(Date dayMin, Date dayMax) throws ApplicationException {
	try {
	    return foodListDaoSql.getFoodListBetween(dayMin, dayMax);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get foodList between "+dayMin.toString()+" and "+dayMax.toString()+"from FoodListDaoSql:\n" + foodListDaoSql.toString(), exc);
	}
    }
    @Override
    public FoodList getFoodListById(int id) throws ApplicationException {
	try {
	    return foodListDaoSql.getFoodListById(id);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get foodList by id = "+id+" from foodListDao:\n" + foodListDaoSql.toString(), exc);
	}
    }
    @Override
    public FoodList newFoodList(FoodList foodList) throws ApplicationException {
	try {
	    return foodListDaoSql.newFoodList(foodList);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't create foodList in foodListDao:\n" + foodList.toString() + foodListDaoSql.toString(), exc);
	}
    }
    @Override
    public FoodList updateFoodList(FoodList foodList) throws ApplicationException {
	try {
	    return foodListDaoSql.updateFoodList(foodList);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't update foodList in foodListDao:\n" + foodList.toString() + foodListDaoSql.toString(), exc);
	}
    }
    @Override
    public Account getAccount(Account account) throws ApplicationException {
	try {
	    return accountDaoSql.getAccount(account);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get account from accountDaoSql:\n" + account.toString() + accountDaoSql.toString(), exc);
	}
    }
    @Override
    public Account getAccountByClient(Client client) throws ApplicationException {
	try {
	    return accountDaoSql.getAccountByClient(client);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get account by client from foodListDao:\n"  + client.toString() + accountDaoSql.toString(), exc);
	}
    }
    @Override
    public Account newAccount(Account account) throws ApplicationException {
	try {
	    return accountDaoSql.newAccount(account);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't create account in foodListDao:\n" + account.toString() + accountDaoSql.toString(), exc);
	}
    }
    @Override
    public Map<String, BigDecimal> totalNutrients(FoodList foodList) throws ApplicationException {
	try {
	    return foodListDaoSql.totalNutrients(foodList);
	} catch (Exception exc) {
	    throw new ApplicationException("Can't get totalNutrients for foodList from foodListDao:\n" + foodList.toString() + foodListDaoSql.toString(), exc);
	}
    }
}
