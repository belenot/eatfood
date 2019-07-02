package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public class FoodDaoSql implements FoodDao {
    private Connection connection;
    private ClientDao clientDao;
    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }
    public void setConnection(Connection connection) { this.connection = connection; }

    public Food addFood(String name, Client client, Map<String, BigDecimal> nutrientMap, boolean common) throws Exception {
	PreparedStatement ps = connection.prepareStatement("INSERT INTO food (name, client, common, calories, protein, carbohydrate, fat) VALUES (?, ?, ?, ?, ?, ?, ?)");
	ps.setString(1, name);
	ps.setInt(2, client.getId());
	ps.setBoolean(3, common);
	ps.setBigDecimal(4, nutrientMap.get("calories") != null ? nutrientMap.get("calories") : new BigDecimal(0));
	ps.setBigDecimal(5, nutrientMap.get("protein") != null ? nutrientMap.get("protein") : new BigDecimal(0));
	ps.setBigDecimal(6, nutrientMap.get("carbohydrate") != null ? nutrientMap.get("carbohydrate") : new BigDecimal(0));
	ps.setBigDecimal(7, nutrientMap.get("fat") != null ? nutrientMap.get("fat") : new BigDecimal(0));
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM food ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    Food food = new Food();
	    food.setId(rs.getInt("id"));
	    food.setName(rs.getString("name").trim());
	    food.setClient(clientDao.getClientById(rs.getInt("client")));
	    food.setCommon(rs.getBoolean("common"));
	    food.setCalories(rs.getBigDecimal("calories"));
	    food.setProtein(rs.getBigDecimal("protein"));
	    food.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
	    food.setFat(rs.getBigDecimal("fat"));
	    return food;
	}
	return null;	    
    }
    public Food getFoodById(int id) throws Exception {
	Food food = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    food = new Food();
	    food.setId(id);
	    food.setClient(clientDao.getClientById(rs.getInt("client")));
	    food.setName(rs.getString("name"));
	    food.setCommon(rs.getBoolean("common"));
	    food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
	    food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
	    food.setCarbohydrate(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
	    food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
	}
	return food;	    
    }
    public List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception {
	List<Food> foodList = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE client = ? ORDER BY id "+(desc?"DESC":"")+" OFFSET ? LIMIT ?");
	ps.setInt(1, client.getId());
	ps.setInt(2, start);
	ps.setInt(3, count);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Food food = new Food();
	    food.setId(rs.getInt("id"));
	    food.setName(rs.getString("name"));
	    food.setClient(client);
	    food.setCommon(rs.getBoolean("common"));
	    food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
	    food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
	    food.setCarbohydrate(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
	    food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
	    foodList.add(food);
	}
	return foodList;
    }
    public List<Food> getFoodByName(String name, int start, int count, boolean desc) throws Exception {
	List<Food> foodList = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE name = ? ORDER BY id "+(desc?"DESC":"")+" OFFSET ? LIMIT ? ");
	ps.setString(1, name);
	ps.setInt(2, start);
	ps.setInt(3, count);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Food food = new Food();
	    food.setId(rs.getInt("id"));
	    food.setName(rs.getString("name"));
	    food.setClient(clientDao.getClientById(rs.getInt("client")));
	    food.setCommon(rs.getBoolean("common"));
	    food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
	    food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
	    food.setCalories(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
	    food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
	    foodList.add(food);
	}
	return foodList;
    }
    public void updateFood(Food food) throws Exception {
	PreparedStatement ps = connection.prepareStatement("UPDATE food SET name = ?, common = ?, calories = ?, protein = ?, carbohydrate = ?, fat = ? WHERE id = ?");
	ps.setString(1, food.getName());
	ps.setBoolean(2, food.isCommon());
	ps.setBigDecimal(3, food.getCalories());
	ps.setBigDecimal(4, food.getProtein());
	ps.setBigDecimal(5, food.getCarbohydrate());
	ps.setBigDecimal(6, food.getFat());
	ps.setInt(7, food.getId());
        ps.execute();
    }
    public boolean deleteFood(Food food) throws Exception {
	PreparedStatement ps = connection.prepareStatement("DELETE FROM food WHERE id = ?");
	ps.setInt(1, food.getId());
	return ps.execute();
    }
	
}
