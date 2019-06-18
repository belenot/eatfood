package com.belenot.eatfood.dao;

import java.sql.SQLException;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodDao {
    void init() throws Exception;
    void destroy() throws Exception;
    
    Food getFood(int id);
    List<Food> getFoodByName(String name);
    List<Food> getFoodByClient(Client client);
    Food addFood(String login);
    Food updateFood(Food food);
    boolean deleteFood(Food food);
}
