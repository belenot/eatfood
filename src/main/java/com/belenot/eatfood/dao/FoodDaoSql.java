package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public class FoodDaoSql implements FoodDao {
    private String connectionAddress;
    private String username;
    private String password;
    private ClientDao clientDao;
    
    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }
    
    
    private Connection connection;
    
    public void init() throws SQLException {
	connection = DriverManager.getConnection(connectionAddress, username, password);
    }

    public void destroy() throws SQLException {
	if (connection != null && !connection.isClosed())
	    connection.close();
    }
    
    public Food getFood(int id) {
	Food food = null;
	try {
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE id = ?");
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		food = new Food();
		food.setId(id);
		food.setClient(clientDao.getClient(rs.getInt("id")));
	        food.setName(rs.getString("name"));
		food.setCalories(rs.getBigDecimal("calories"));
	        food.setProtein(rs.getBigDecimal("protein"));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
		food.setFat(rs.getBigDecimal("fat"));
	    }
	} catch (SQLException exc) { /*log*/ }
	return food;	    
    }
    
    public List<Food> getFoodByClient(Client client) { return null; }
    public List<Food> getFoodByName(String name) { return null; }
    public Food addFood(String login) { return null; }
    public Food updateFood(Food food) { return null; }
    public boolean deleteFood(Food food) { return false; }
}
