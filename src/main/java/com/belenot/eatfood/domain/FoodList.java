package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodList {
    private int id;
    private Client client;
    private Date day;
    private Map<Food, BigDecimal> foodRecordMap = new HashMap<>();
    
    public void setId(int id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setDay(Date day) { this.day = day; }
    public void setFoodRecordMap(Map<Food, BigDecimal> foodRecordMap) { this.foodRecordMap = foodRecordMap; }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public Date getDay() { return day; }
    public Map<Food, BigDecimal> getFoodRecordMap() { return foodRecordMap; }

    public boolean addFoodRecord(Food food, BigDecimal gram) {
	if (foodRecordMap.containsKey(food)) {
	    return false;
	}
	foodRecordMap.put(food, gram);
	return true;
    }
    public boolean removeFoodRecord(Food food) {
	if (foodRecordMap.containsKey(food)) {
	    foodRecordMap.remove(food);
	    return true;
	}
	return false;
    }
    public boolean updateFoodRecord(Food food, BigDecimal gram) {
	if (foodRecordMap.containsKey(food)) {
	    foodRecordMap.put(food, gram);
	    return true;
	}
	return false;
    }

    @Override
    public String toString() {
	String str = String.format("FoodList:\n\tId: %d;\n\tClient: %d;\n\tDay: %s;\n\tSize: %d;\n",
				   id, client != null ? client.getId() : null, day.toString(), foodRecordMap.size());
	return str;
    }
    public List<String> printRecords() {
	List<String> printedRecords = new ArrayList<>(foodRecordMap.size());
	for (Food food : foodRecordMap.keySet()) {
	    printedRecords.add(String.format("%d: %.2f", food.getId(), foodRecordMap.get(food).doubleValue()));
	}
	return printedRecords;
    }
	
    
}
	
