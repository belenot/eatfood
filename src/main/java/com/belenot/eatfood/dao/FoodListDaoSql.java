package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.FoodList;

public class FoodListDaoSql implements FoodListDao {
    private Connection connection;
    private FoodDaoSql foodDaoSql;
    private ClientDaoSql clientDaoSql;

    public void setConnection(Connection connection) { this.connection = connection; }
    public void setFoodDaoSql(FoodDaoSql foodDaoSql) { this.foodDaoSql = foodDaoSql; }
    public void setClientDaoSql(ClientDaoSql clientDaoSql) { this.clientDaoSql = clientDaoSql; }
    @Override
    public FoodList newFoodList(FoodList foodList) throws Exception {
	FoodList foodListResult = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO food_list (client, day) VALUES (?, ?)");
	ps.setInt(1, foodList.getClient().getId());
	ps.setDate(2, new Date(foodList.getDay().getTime()));
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM food_list ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    foodListResult = new FoodList();
	    foodListResult.setId(rs.getInt("id"));
	    foodListResult.setClient(clientDaoSql.getClientById(rs.getInt("client")));
	    foodListResult.setDay(rs.getDate("date"));
	}
	return foodListResult;
    }
    @Override
    public FoodList updateFoodList(FoodList foodList) throws Exception {
	FoodList foodListResult = null;
	PreparedStatement ps = connection.prepareStatement("UPDATE food_list SET client = ?, day = ? WHERE id = ?");
	ps.setInt(1, foodList.getClient().getId());
	ps.setDate(2, new Date(foodList.getDay().getTime()));
	ps.setInt(3, foodList.getId());
	ps.execute();
	ps = connection.prepareStatement("DELETE FROM food_list_record WHERE food_list = ?");
	ps.setInt(1, foodList.getId());
	ps.execute();
	ps = connection.prepareStatement("INSERT INTO food_list_record (food_list, food, gram) VALUES (?, ?, ?)");
	ps.setInt(1, foodList.getId());
	for (Food food : foodList.getFoodRecordMap().keySet()) {
	    ps.setInt(2, food.getId());
	    ps.setBigDecimal(3, foodList.getFoodRecordMap().get(food));
	    ps.execute();
	}
	return foodList;
    }
	
    @Override
    public FoodList getFoodListById(int id) throws Exception {
	FoodList foodListResult = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM food_list WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    foodListResult = new FoodList();
	    foodListResult.setId(rs.getInt("id"));
	    foodListResult.setClient(clientDaoSql.getClientById(rs.getInt("client")));
	    foodListResult.setDay(rs.getDate("day"));
	} else { return foodListResult; }
	ps = connection.prepareStatement("SELECT * FROM food_list_record WHERE food_list = ?");
	while (rs.next()) {
	    foodListResult.addFoodRecord(foodDaoSql.getFoodById(rs.getInt("food")), rs.getBigDecimal("gram"));
	}
	return foodListResult;
    }
    @Override
    public List<FoodList> getFoodListAfter(java.util.Date day, int offset, int limit) throws Exception {
	List<FoodList> foodListListResult = new ArrayList<>();
	String sql = String.format("SELECT id FROM food_list WHERE day > ? %s %s", offset > 0 ? " OFFSET " + offset : "", limit > 0 ? " LIMIT " + limit : "");
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setDate(1, new Date(day.getTime()));
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    FoodList foodList = getFoodListById(rs.getInt("id"));
	    foodListListResult.add(foodList);
	}
	return foodListListResult;
    }
    @Override
    public List<FoodList> getFoodListBefore(java.util.Date day, int offset, int limit) throws Exception {
	List<FoodList> foodListListResult = new ArrayList<>();
	String sql = String.format("SELECT id FROM food_list WHERE day < ? %s %s", offset > 0 ? " OFFSET " + offset : "", limit > 0 ? " LIMIT " + limit : "");
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setDate(1, new Date(day.getTime()));
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    FoodList foodList = getFoodListById(rs.getInt("id"));
	    foodListListResult.add(foodList);
	}
	return foodListListResult;
    }
    @Override
    public List<FoodList> getFoodListBetween(java.util.Date dayMin,java.util.Date dayMax) throws Exception {
	List<FoodList> foodListListResult = new ArrayList<>();
	String sql = String.format("SELECT id FROM food_list WHERE day => ? AND day <= ?");
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setDate(1, new Date(dayMin.getTime()));
	ps.setDate(2, new Date(dayMax.getTime()));
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    FoodList foodList = getFoodListById(rs.getInt("id"));
	    foodListListResult.add(foodList);
	}
	return foodListListResult;
    }
    

    @Override
    public Map<String, BigDecimal> totalNutrients(FoodList foodList) throws Exception{
	Map<String, BigDecimal> totalNutrientMap = new HashMap<>();
	PreparedStatement ps = connection.prepareStatement("SELECT sum(calories), sum(protein), sum(carbohydrate), sum(fat) FROM food_list_record WHERE food_list = ?");
	ps.setInt(1, foodList.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    totalNutrientMap.put("calories", rs.getBigDecimal(1));
	    totalNutrientMap.put("protein", rs.getBigDecimal(2));
	    totalNutrientMap.put("carbohydrate", rs.getBigDecimal(3));
	    totalNutrientMap.put("fat", rs.getBigDecimal(4));
	}
	return totalNutrientMap;
    }
    @Override
    public String toString() {
	String str = String.format("FoodListDaoSql: %s, ClientDaoSqlSql=%s, FoodDaoSql=%s", connection != null ? connection.toString() : null, clientDaoSql, foodDaoSql);
	return str;
    }	
	
}    
