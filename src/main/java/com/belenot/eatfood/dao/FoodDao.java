package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodDao<T extends FoodCriteria<?>> {
    Food newFood(Food food) throws Exception;
    Food updateFood(Food food) throws Exception;
    Food getFoodById(int id) throws Exception;
    List<Food> getFoodByCriteria(T foodCriteria) throws Exception;
    Food deleteFood(Food food) throws Exception;
    
    Map<String, BigDecimal> totalNutrients(Client client) throws Exception;
}
