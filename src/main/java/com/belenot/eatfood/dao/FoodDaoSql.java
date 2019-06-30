package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

public class FoodDaoSql implements FoodDao {
    private String connectionAddress;
    private String username;
    private String password;
    private boolean manualRegistre;
    @Autowired
    private ClientDao clientDao;
    
    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setClientDao(ClientDao clientDao) { this.clientDao = clientDao; }
    public void setManualRegistre(boolean manualRegistre) { this.manualRegistre = manualRegistre; }
    
    private Connection connection;


    public void init() {
	try {
	    if (manualRegistre) Class.forName("org.postgresql.Driver");
	    connection = DriverManager.getConnection(connectionAddress, username, password);
	} catch (SQLException | ClassNotFoundException exc) {
	    String msg = String.format("Can't connect to FoodDao(%s)", connectionAddress);
	    LogManager.getLogger().fatal(msg, exc);
	}
    }
    public void destroy() throws ApplicationException {
	try {
	    if (connection != null && !connection.isClosed())
		connection.close();
	} catch (SQLException exc) {
	    String msg = String.format("Can't close connection with FoodDao(%s)", connectionAddress);
	    LogManager.getLogger().warn(msg, exc);
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
		food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
	        food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
		food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
		food.setGram(rs.getBigDecimal("gram").setScale(2, RoundingMode.FLOOR));
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
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE client = ? ORDER BY id OFFSET ? LIMIT ?");
	    ps.setInt(1, client.getId());
	    ps.setInt(2, start);
	    ps.setInt(3, count);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name"));
		food.setClient(client);
		food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
		food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
		food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
		food.setGram(rs.getBigDecimal("gram").setScale(2, RoundingMode.FLOOR));
		foodList.add(food);
	    }
	    return foodList;
	} catch(SQLException exc) {
	    String msg = String.format("Can't get food by client{id = %d, login = %s}", client.getId(), client.getLogin());
	    throw new ApplicationException(msg, exc);
	}
    }
    public List<Food> getFoodByClientLast(Client client, int start, int count) throws ApplicationException {
	try {
	    List<Food> foodList = new ArrayList<>(count);
	    PreparedStatement st = connection.prepareStatement("SELECT * FROM food WHERE client = ? ORDER BY id DESC OFFSET ? LIMIT ?");
	    st.setInt(1, client.getId());
	    st.setInt(2, start);
	    st.setInt(3, count);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name"));
		food.setClient(client);
		food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
		food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
		food.setCarbohydrate(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
		food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
		food.setGram(rs.getBigDecimal("gram").setScale(2, RoundingMode.FLOOR));
		foodList.add(food);
	}
	return foodList;
	} catch (SQLException exc) {
	    String msg = String.format("Can't get last %d food rows for client { id = %d, login = %s}", count, client.getId(), client.getLogin());
	    throw new ApplicationException(msg, exc);
	}
    }
    public Food addFood(String name, Client client, Map<String, BigDecimal> nutrientMap, BigDecimal gram) throws ApplicationException {
	try {
	    PreparedStatement ps = connection.prepareStatement("INSERT INTO food (name, client, calories, protein, carbohydrate, fat, gram) VALUES (?, ?, ?, ?, ?, ?, ?)");
	    ps.setString(1, name);
	    ps.setInt(2, client.getId());
	    ps.setBigDecimal(3, nutrientMap.get("calories") != null ? nutrientMap.get("calories") : new BigDecimal(0));
	    ps.setBigDecimal(4, nutrientMap.get("protein") != null ? nutrientMap.get("protein") : new BigDecimal(0));
	    ps.setBigDecimal(5, nutrientMap.get("carbohydrate") != null ? nutrientMap.get("carbohydrate") : new BigDecimal(0));
	    ps.setBigDecimal(6, nutrientMap.get("fat") != null ? nutrientMap.get("fat") : new BigDecimal(0));
	    ps.setBigDecimal(7, gram);
	    ps.execute();
	    ps = connection.prepareStatement("SELECT * FROM food ORDER BY id DESC LIMIT 1");
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name").trim());
		food.setClient(client);
		food.setCalories(nutrientMap.get("calories") != null ? nutrientMap.get("calories").setScale(2, RoundingMode.FLOOR) : new BigDecimal(0));
		food.setProtein(nutrientMap.get("protein") != null ? nutrientMap.get("protein").setScale(2, RoundingMode.FLOOR) : new BigDecimal(0));
		food.setCarbohydrate(nutrientMap.get("carbohydrate") != null ? nutrientMap.get("carbohydrate").setScale(2, RoundingMode.FLOOR) : new BigDecimal(0));
		food.setFat(nutrientMap.get("fat") != null ? nutrientMap.get("fat").setScale(2, RoundingMode.FLOOR) : new BigDecimal(0));
		food.setGram(gram.setScale(2, RoundingMode.FLOOR));
		if (!food.getName().equals(name)) {
		    String msg = String.format("Can't fetch added food with name = \"" + name + "\" to FoodDao(last added food's name and parameter name are not equal(%s!=%s))", name, food.getName().trim());
		    throw new ApplicationException(msg);
		}
	    return food;
	    }
	    return null;
	} catch (SQLException exc) {
	    String msg = String.format("Can't add food{name=%s} to FoodDao", name);
	    throw new ApplicationException(msg, exc);
	}
    }
    public List<Food> getFoodByName(String name) throws ApplicationException {
	try {
	    List<Food> foodList = new ArrayList<>();
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM food WHERE name = ?");
	    ps.setString(1, name);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Food food = new Food();
		food.setId(rs.getInt("id"));
		food.setName(rs.getString("name"));
		food.setClient(clientDao.getClient(rs.getInt("client")));
		food.setCalories(rs.getBigDecimal("calories").setScale(2, RoundingMode.FLOOR));
		food.setProtein(rs.getBigDecimal("protein").setScale(2, RoundingMode.FLOOR));
		food.setCalories(rs.getBigDecimal("carbohydrate").setScale(2, RoundingMode.FLOOR));
		food.setFat(rs.getBigDecimal("fat").setScale(2, RoundingMode.FLOOR));
		foodList.add(food);
		food.setGram(rs.getBigDecimal("gram").setScale(2, RoundingMode.FLOOR));
	    }
	    return foodList;
	} catch (SQLException exc) {
	    String msg = String.format("Can't get food by name %s", name);
	    throw new ApplicationException(msg, exc);
	}
    }
    public boolean updateFood(Food food) throws ApplicationException {
	try {
	    PreparedStatement ps = connection.prepareStatement("UPDATE food SET name = ?, calories = ?, protein = ?, carbohydrate = ?, fat = ?, gram = ? WHERE id = ?");
	    ps.setString(1, food.getName());
	    ps.setBigDecimal(2, food.getCalories());
	    ps.setBigDecimal(3, food.getProtein());
	    ps.setBigDecimal(4, food.getCarbohydrate());
	    ps.setBigDecimal(5, food.getFat());
	    ps.setBigDecimal(6, food.getGram());
	    ps.setInt(7, food.getId());
	    return ps.execute();
	} catch (SQLException exc) {
	    String msg = String.format("Can't update food { id = %d }", food.getId());
	    throw new ApplicationException(msg, exc);
	}
    }
    public boolean deleteFood(Food food) throws ApplicationException {
	try {
	    PreparedStatement ps = connection.prepareStatement("DELETE FROM food WHERE id = ?");
	    ps.setInt(1, food.getId());
	    return ps.execute();
	} catch (SQLException exc) {
	    String msg = String.format("Can't delete food { id = %d client = %s }", food.getId(), food.getClient().getLogin());
	    throw new ApplicationException(msg, exc);
	}
    }
    public Map<String, BigDecimal> totalNutrients(Client client) throws ApplicationException {
	try {
	    Map<String, BigDecimal> totalNutrients = new HashMap<>();
	    PreparedStatement ps = connection.prepareStatement("SELECT sum(calories / 100 * gram), sum(protein / 100 * gram), sum(carbohydrate / 100 * gram), sum(fat / 100 * gram) FROM food WHERE client = ?");
	    ps.setInt(1, client.getId());
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		BigDecimal calories = rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : new BigDecimal(0);
		BigDecimal protein = rs.getBigDecimal(2) != null ? rs.getBigDecimal(2) : new BigDecimal(0);
		BigDecimal carbohydrate = rs.getBigDecimal(3) != null ? rs.getBigDecimal(3) : new BigDecimal(0);
		BigDecimal fat = rs.getBigDecimal(4) != null ? rs.getBigDecimal(4) : new BigDecimal(0);

		totalNutrients.put("calories", calories.setScale(2, RoundingMode.FLOOR));
		totalNutrients.put("protein", protein.setScale(2, RoundingMode.FLOOR));
		totalNutrients.put("carbohydrate", carbohydrate.setScale(2, RoundingMode.FLOOR));
		totalNutrients.put("fat", fat.setScale(2, RoundingMode.FLOOR));
	    }
	    return totalNutrients;
	} catch (SQLException exc) {
	    throw new ApplicationException(String.format("Can' account total nutrients for client { id = %d, login = %s }", client.getId(), client.getLogin()), exc);
	}
    }
	    
	
}
