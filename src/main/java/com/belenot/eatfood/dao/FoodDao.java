package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;

public interface FoodDao {
    Food addFood(String name, Client client, Map<String, BigDecimal> nutrientMap, boolean common) throws Exception;
    Food getFoodById(int id) throws Exception;
    List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception;
    /**
     * Return list, but assumed, that name is unique, and list returns will only for a while
     */
    List<Food> getFoodByName(String name, int start, int count, boolean desc) throws Exception;
    void updateFood(Food food) throws Exception;
    boolean deleteFood(Food food) throws Exception;
}
