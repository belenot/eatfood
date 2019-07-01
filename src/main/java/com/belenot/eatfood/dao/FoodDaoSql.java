package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public class FoodDaoSql implements FoodDao<FoodCriteriaSql> {
    private Connection connection;
    private ClientDao clientDao;
    public void setConnection(Connection connection) { this.connection = connection; }
    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }

    @Override
    public Food newFood(Food food) throws Exception {
	Food foodResult = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO food (name, calories, protein, carbohydrate, fat, client, ancestor) VALUES (?, ?, ?, ?, ?, ?, ?)");
	ps.setString(1, food.getName());
	ps.setBigDecimal(2, food.getCalories());
	ps.setBigDecimal(3, food.getProtein());
	ps.setBigDecimal(4, food.getCarbohydrate());
	ps.setBigDecimal(5, food.getFat());
	ps.setInt(6, food.getClient().getId());
	ps.setInt(7, food.getClient().getId());
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM food ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    foodResult = new Food();
	    foodResult.setName(rs.getString("name"));
	    foodResult.setCalories(rs.getBigDecimal("calories"));
	    foodResult.setProtein(rs.getBigDecimal("protein"));
	    foodResult.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
	    foodResult.setFat(rs.getBigDecimal("fat"));
	    foodResult.setClient(clientDao.getClientById(rs.getInt("client")));
	    foodResult.setAncestor(getFoodById(rs.getInt("ancestor")));
	}
	return foodResult;
    }
    @Override
    public Food updateFood(Food food) throws Exception {
	Food foodResult = null;
	PreparedStatement ps = connection.prepareStatement("UPDATE food SET name = ?, calories = ?, protein = ?, carbohydrate = ?, fat = ?, client = ?, ancestor = ? WHERE id = ?");
	ps.setString(1, food.getName());
	ps.setBigDecimal(2, food.getCalories());
	ps.setBigDecimal(3, food.getProtein());
	ps.setBigDecimal(4, food.getCarbohydrate());
	ps.setBigDecimal(5, food.getFat());
	ps.setInt(6, food.getClient().getId());
	ps.setInt(7, food.getAncestor().getId());
	ps.setInt(8, food.getId());
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM food WHERE id = ?");
	ps.setInt(1, food.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    foodResult = new Food();
	    foodResult.setName(rs.getString("name"));
	    foodResult.setCalories(rs.getBigDecimal("calories"));
	    foodResult.setProtein(rs.getBigDecimal("protein"));
	    foodResult.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
	    foodResult.setFat(rs.getBigDecimal("fat"));
	    foodResult.setClient(clientDao.getClientById(rs.getInt("client")));
	    foodResult.setAncestor(getFoodById(rs.getInt("ancestor")));
	    foodResult.setId(rs.getInt("id"));
	}
	return foodResult;
    }
    @Override
    public Food getFoodById(int id) throws Exception {
	Food foodResult = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    foodResult = new Food();
	    foodResult.setName(rs.getString("name"));
	    foodResult.setCalories(rs.getBigDecimal("calories"));
	    foodResult.setProtein(rs.getBigDecimal("protein"));
	    foodResult.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
	    foodResult.setFat(rs.getBigDecimal("fat"));
	    foodResult.setClient(clientDao.getClientById(rs.getInt("client")));
	    foodResult.setAncestor(getFoodById(rs.getInt("ancestor")));
	    foodResult.setId(rs.getInt("id"));
	}
	return foodResult;
    }
    @Override
    public Food deleteFood(Food food) throws Exception {
	Food foodResult = null;
	PreparedStatement ps = connection.prepareStatement("DELETE FROM food WHERE id = ?");
	ps.setInt(1, food.getId());
	ps.execute();
	foodResult = new Food();
	foodResult.setName(food.getName());
	foodResult.setCalories(food.getCalories());
	foodResult.setProtein(food.getProtein());
	foodResult.setCarbohydrate(food.getCarbohydrate());
	foodResult.setFat(food.getFat());
	foodResult.setClient(food.getClient());
	foodResult.setAncestor(food.getAncestor());
	return foodResult;
    }
    @Override
    public List<Food> getFoodByCriteria(FoodCriteriaSql foodCriteriaSql) throws Exception {
	List<Food> foodListResult = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE " + foodCriteriaSql.criteria());
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Food food = new Food();
	    food.setId(rs.getInt("id"));
	    food.setName(rs.getString("name"));
	    food.setCalories(rs.getBigDecimal("calories"));
	    food.setProtein(rs.getBigDecimal("protein"));
	    food.setCarbohydrate(rs.getBigDecimal("carbohydrate"));
	    food.setFat(rs.getBigDecimal("fat"));
	    food.setClient(clientDao.getClientById(rs.getInt("client")));
	    food.setAncestor(getFoodById(rs.getInt("ancestor")));
	    foodListResult.add(food);
	}
	return foodListResult;
    }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception {
	Map<String, BigDecimal> totalNutrientMap = new HashMap<>();
	PreparedStatement ps = connection.prepareStatement("SELECT sum(calories), sum(protein), sum(carbohydrate), sum(fat) FROM food WHERE client = ?");
	ps.setInt(1, client.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    totalNutrientMap.put("calories", rs.getBigDecimal(1));
	    totalNutrientMap.put("protein", rs.getBigDecimal(2));
	    totalNutrientMap.put("carbohydrate", rs.getBigDecimal(3));
	    totalNutrientMap.put("fat", rs.getBigDecimal(4));
	}
	return totalNutrientMap;
    }
	
}
