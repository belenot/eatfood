package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodDao {
    Food addFood(Food food) throws Exception;
    Food getFoodById(int id) throws Exception;
    List<Food> getFoodByClient(Client client, int start, int count, boolean desc) throws Exception;
    /**
     * Return list, but assumed, that food.name and food.client are unique and not null, and list returns will only for a while
     */
    List<Food> getFoodByName(Food food, int start, int count, boolean desc) throws Exception;
    void updateFood(Food food) throws Exception;
    boolean deleteFood(Food food) throws Exception;
}
