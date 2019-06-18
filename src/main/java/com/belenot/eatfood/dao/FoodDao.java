package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodDao {
    void init() throws Exception;
    
    Food getFood(long id);
    List<Food> getFoodByName(String name);
    List<Food> getFoodByClient(Client client);
    Food addFood(String login);
    Food updateFood(Food food);
    boolean deleteFood(Food food);
}
