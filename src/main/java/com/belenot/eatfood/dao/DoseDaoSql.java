package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

public class DoseDaoSql implements DoseDao {

    private Connection connection;
    private FoodDao foodDao;
    public void setConnection(Connection connection) { this.connection = connection; }
    public void setFoodDao(FoodDao foodDao) { this.foodDao = foodDao; }
    
    @Override
    public Dose newDose(Dose dose) throws Exception {
	PreparedStatement ps = connection.prepareStatement("INSERT INTO dose (food, gram, date) VALUES (?, ?, ?)");
	ps.setInt(1, dose.getFood().getId());
	ps.setBigDecimal(2, dose.getGram());
	ps.setDate(3, new java.sql.Date(dose.getDate().getTime()));
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM dose ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    Dose doseReturn = new Dose();
	    doseReturn.setId(rs.getInt("id"));
	    doseReturn.setFood(foodDao.getFoodById(rs.getInt("food")));
	    doseReturn.setDate(rs.getDate("date"));
	    doseReturn.setGram(rs.getBigDecimal("gram"));
	}
	return null;
    }
    @Override
    public Dose getDoseById(int id) throws Exception {
	Dose dose = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM dose WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();       
	if (rs.next()) {
	    dose = new Dose();
	    dose.setId(rs.getInt("id"));
	    dose.setFood(foodDao.getFoodById(rs.getInt("food")));
	    dose.setDate(rs.getDate("date"));
	    dose.setGram(rs.getBigDecimal("gram"));
	}
	return dose;
    }
    @Override
    public List<Dose> getDoseByFood(Food food, int offset, int limit, boolean desc) throws Exception {
	List<Dose> doseList = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM dose WHERE food = ? ORDER BY id "+(desc?"DESC":"")+" OFFSET ? LIMIT ?");
	ps.setInt(1, food.getId());
	ps.setInt(2, offset);
	ps.setInt(3, limit);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Dose dose = new Dose();
	    dose.setId(rs.getInt("id"));
	    dose.setFood(foodDao.getFoodById(rs.getInt("food")));
	    dose.setDate(rs.getDate("date"));
	    dose.setGram(rs.getBigDecimal("gram"));
	    doseList.add(dose);
	}
	return doseList;
    }
    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc) throws Exception {
	List<Dose> doseList = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM dose WHERE food IN (SELECT id FROM food WHERE client = ?) ORDER BY id "+(desc?"DESC":"")+" OFFSET ? LIMIT ?");
	ps.setInt(1, client.getId());
	ps.setInt(2, offset);
	ps.setInt(3, limit);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Dose dose = new Dose();
	    dose.setId(rs.getInt("id"));
	    dose.setFood(foodDao.getFoodById(rs.getInt("food")));
	    dose.setDate(rs.getDate("date"));
	    dose.setGram(rs.getBigDecimal("gram"));
	    doseList.add(dose);
	}
	return doseList;
    }
    @Override
    public List<Dose> getDoseByClient(Client client, int offset, int limit, boolean desc, Date date) throws Exception {
	List<Dose> doseList = new ArrayList<>();
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM dose WHERE food IN (SELECT id FROM food WHERE client = ?) AND date = ? ORDER BY id "+(desc?"DESC":"")+" OFFSET ? LIMIT ?");
	ps.setInt(1, client.getId());
	ps.setDate(2, new java.sql.Date(date.getTime()));
	ps.setInt(3, offset);
	ps.setInt(4, limit);
	ResultSet rs = ps.executeQuery();
	while (rs.next()) {
	    Dose dose = new Dose();
	    dose.setId(rs.getInt("id"));
	    dose.setFood(foodDao.getFoodById(rs.getInt("food")));
	    dose.setDate(rs.getDate("date"));
	    dose.setGram(rs.getBigDecimal("gram"));
	    doseList.add(dose);
	}
	return doseList;
    }
    @Override
    public void updateDose(Dose dose) throws Exception {
	PreparedStatement ps = connection.prepareStatement("UPDATE dose SET gram = ?, date = ? WHERE id = ?");
	ps.setBigDecimal(1, dose.getGram());
	ps.setDate(2, new java.sql.Date(dose.getDate().getTime()));
	ps.setInt(3, dose.getId());
	ps.execute();
    }
    @Override
    public void deleteDose(Dose dose) throws Exception {
	PreparedStatement ps = connection.prepareStatement("DELETE FROM dose WHERE id = ?");
	ps.setInt(1, dose.getId());
	ps.execute();
    }
    @Override
    public Map<String, BigDecimal> totalNutrients(Client client) throws Exception {
	Map<String, BigDecimal> totalNutrients = new HashMap<>();
	PreparedStatement ps = connection.prepareStatement("SELECT sum(f.calories*d.gram/100), sum(f.protein*d.gram/100), sum(f.carbohydrate*d.gram/100), sum(f.fat*d.gram/100) FROM food f INNER JOIN dose d ON f.id = d.food WHERE f.id in (SELECT food FROM client WHERE id = ?)");
	ps.setInt(1, client.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    totalNutrients.put("calories", rs.getBigDecimal(1));
	    totalNutrients.put("protein", rs.getBigDecimal(2));
	    totalNutrients.put("carbohydrate", rs.getBigDecimal(3));
	    totalNutrients.put("fat", rs.getBigDecimal(4));
	}
	return totalNutrients;
    }
}
