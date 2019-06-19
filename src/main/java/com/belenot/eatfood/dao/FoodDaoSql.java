package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;

public class FoodDaoSql implements FoodDao {
    private String connectionAddress;
    private String username;
    private String password;
    @Autowired
    private ClientDao clientDao;
    
    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }
    
    
    private Connection connection;
    
    public void init() throws ApplicationException {
	try {
	    connection = DriverManager.getConnection(connectionAddress, username, password);
	} catch (SQLException exc) {
	    String msg = String.format("Can't connect to FoodDao(%s)", connectionAddress);
	    throw new ApplicationException(msg, exc);
	}
    }
    public void destroy() throws ApplicationException {
	try {
	    if (connection != null && !connection.isClosed())
		connection.close();
	} catch (SQLException exc) {
	    String msg = String.format("Can't close connection with FoodDao(%s)", connectionAddress);
	    throw new ApplicationException(msg, exc);
	}
    }
    public Food getFood(int id) throws ApplicationException {
	try{
	    Food food = null;
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE id = ?");
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		food = new Food();
		food.setId(id);
		food.setClient(clientDao.getClient(rs.getInt("client")));
	        food.setName(rs.getString("name"));
		food.setCalories(rs.getBigDecimal("calories"));
	        food.setProtein(rs.getBigDecimal("protein"));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
		food.setFat(rs.getBigDecimal("fat"));
	    }
	    return food;	    
	} catch (SQLException exc) {
	    String msg = String.format("Can't get food from FoodDao with id = %d", id);
	    throw new ApplicationException(msg, exc);
	}
    }
    public List<Food> getFoodByClient(Client client, int start, int count) throws ApplicationException {
	try {
	    List<Food> foodList = new ArrayList<>();
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE client = ? AND id > ? ORDER BY id LIMIT ?");
	    ps.setInt(1, client.getId());
	    ps.setInt(2, start);
	    ps.setInt(3, count);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name"));
		food.setClient(client);
		food.setCalories(rs.getBigDecimal("calories"));
		food.setProtein(rs.getBigDecimal("protein"));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
		food.setFat(rs.getBigDecimal("fat"));
		foodList.add(food);
	    }
	    return foodList;
	} catch(SQLException exc) {
	    String msg = String.format("Can't get food by client{id = %d, login = %s}", client.getId(), client.getLogin());
	    throw new ApplicationException(msg, exc);
	}
    }
    public Food addFood(String name, Client client, Map<String, BigDecimal> nutrientMap) throws ApplicationException {
	try {
	    PreparedStatement ps = connection.prepareStatement("INSERT INTO food (name, client, calories, protein, carbohydrate, fat) VALUES (?, ?, ?, ?, ?, ?)");
	    ps.setString(1, name);
	    ps.setInt(2, client.getId());
	    ps.setBigDecimal(3, nutrientMap.get("calories") != null ? nutrientMap.get("calories") : new BigDecimal(0));
	    ps.setBigDecimal(4, nutrientMap.get("protein") != null ? nutrientMap.get("protein") : new BigDecimal(0));
	    ps.setBigDecimal(5, nutrientMap.get("carbohydrate") != null ? nutrientMap.get("carbohydrate") : new BigDecimal(0));
	    ps.setBigDecimal(6, nutrientMap.get("fat") != null ? nutrientMap.get("fat") : new BigDecimal(0));
	    ps.execute();
	    ps = connection.prepareStatement("SELECT * FROM food ORDER BY id DESC LIMIT 1");
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name").trim());
		food.setCalories(nutrientMap.get("calories") != null ? nutrientMap.get("calories") : new BigDecimal(0));
		food.setProtein(nutrientMap.get("protein") != null ? nutrientMap.get("protein") : new BigDecimal(0));
		food.setCarbohydrate(nutrientMap.get("carbohydrate") != null ? nutrientMap.get("carbohydrate") : new BigDecimal(0));
		food.setFat(nutrientMap.get("fat") != null ? nutrientMap.get("fat") : new BigDecimal(0));
		if (!food.getName().equals(name)) {
		    String msg = String.format("Can't fetch added food with name = \"" + name + "\" to FoodDao(last added food's name and parameter name are not equal(%s!=%s))", name, food.getName().trim());
		    throw new ApplicationException(msg);
		}
	    return food;
	    }
	    return null;
	} catch (SQLException exc) {
	    String msg = String.format("Can't add food{name=%s} to FoodDao");
	    throw new ApplicationException(msg, exc);
	}
    }
    public List<Food> getFoodByName(String name) throws ApplicationException {
	throw new ApplicationException("Method(FoodDao.getFoodByName(String name)) isn't supported yet");
    }
    public Food updateFood(Food food) throws ApplicationException {
	throw new ApplicationException("Method FoodDao.updateFood(Food food)) isn't supported yet");
    }
    public boolean deleteFood(Food food) throws ApplicationException {
	throw new ApplicationException("Method FoodDao.deleteFood(Food food)) isn't supported yet");
    }
}
