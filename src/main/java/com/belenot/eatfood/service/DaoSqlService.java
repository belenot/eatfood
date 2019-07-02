package com.belenot.eatfood.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.dao.ClientDaoSql;
import com.belenot.eatfood.dao.DoseDaoSql;
import com.belenot.eatfood.dao.FoodDaoSql;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

import org.apache.logging.log4j.LogManager;

public class DaoSqlService implements DaoService, AutoCloseable {
    private Connection connection;
    private String address;
    private String username;
    private String password;
    private boolean connected;
    private ClientDaoSql clientDaoSql;
    private FoodDaoSql foodDaoSql;
    private DoseDaoSql doseDaoSql;

    public void setAddress(String address) { this.address = address; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setClientDaoSql(ClientDaoSql clientDaoSql) { this.clientDaoSql = clientDaoSql; }
    public void setFoodDaoSql(FoodDaoSql foodDaoSql) { this.foodDaoSql = foodDaoSql; }
    public void setDoseDaoSql(DoseDaoSql doseDaoSql) { this.doseDaoSql = doseDaoSql; }

    public boolean isConnected() { return connected; }
    public void connect() {
	try {
	    connection = DriverManager.getConnection(address, username, password);
	    clientDaoSql.setConnection(connection);
	    foodDaoSql.setConnection(connection);
	    doseDaoSql.setConnection(connection);
	    connected = true;
	} catch (SQLException exc) {
	    LogManager.getLogger().error("Can't connection to database %s %s %s", address, username, password);
	    connected = false;
	}
    }
    @Override
    public Client addClient(String login, String password, String name, String surname, String email) throws Exception {
	return clientDaoSql.addClient(login, password, name, surname, email);
    }
    @Override
    public boolean deleteClient(Client client) throws Exception {
	return clientDaoSql.deleteClient(client);
    }
    @Override
    public Client getClientById(int id) throws Exception {
	return clientDaoSql.getClientById(id);
    }
    @Override
    public Client getClientByLogin(String login, String password) throws Exception {
	return clientDaoSql.getClientByLogin(login, password);
    }
    @Override
    public void updateClient(Client client) throws Exception {
	clientDaoSql.updateClient(client);
    }
    @Override
    public Food addFood(String name, Client client, Map<String, BigDecimal> nutrientMap, boolean common)
	throws Exception {
	return foodDaoSql.addFood(name, client, nutrientMap, common);
    }
    @Override
    public boolean deleteFood(Food food) throws Exception {
	return foodDaoSql.deleteFood(food);
    }
    @Override
    public List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception {
	return foodDaoSql.getFoodByClient(client, start, count, desc);
    }
    @Override
    public Food getFoodById(int id) throws Exception {
	return foodDaoSql.getFoodById(id);
    }
    @Override
    public List<Food> getFoodByName(String name, int start, int count, boolean desc) throws Exception {
	return foodDaoSql.getFoodByName(name, start, count, desc);
    }
    @Override
    public void updateFood(Food food) throws Exception {
	foodDaoSql.updateFood(food);
    }
    @Override
    public void deleteDose(Dose dose) throws Exception {
	doseDaoSql.deleteDose(dose);
    }
    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc) throws Exception {
	return doseDaoSql.getDoseByClient(client, offset, limit, desc);
    }
    @Override
    public List<Dose> getDoseByFood(Food food, int offset, int limit, boolean desc) throws Exception {
	return doseDaoSql.getDoseByFood(food, offset, limit, desc);
    }
    @Override
    public Dose getDoseById(int id) throws Exception {
	return doseDaoSql.getDoseById(id);
    }
    @Override
    public Dose newDose(Food food, BigDecimal gram, Date date) throws Exception {
	return doseDaoSql.newDose(food, gram, date);
    }
    @Override
    public void updateDose(Dose dose) throws Exception {
	doseDaoSql.updateDose(dose);
    }
    @Override
    public void close() throws Exception {
	connection.close();
    }

}
