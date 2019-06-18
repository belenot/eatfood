package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public class FoodDaoSql implements FoodDao {
    private String connectionAddress;
    private String username;
    private String password;
    
    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    
    private Connection connection;
    
    public void init() throws SQLException {
	connection = DriverManager.getConnection(connectionAddress, username, password);
    }
    
    public Food getFood(long id) { return null; }
    public List<Food> getFoodByName(String name) { return null; }
    public List<Food> getFoodByClient(Client client) { return null; }
    public Food addFood(String login) { return null; }
    public Food updateFood(Food food) { return null; }
    public boolean deleteFood(Food food) { return false; }
}
