package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Food;

public interface FoodDao {
    Food getFood(long id);
    List<Food> getFoodByName(String name);
    Food addFood(String login);
    Food updateFood(Food food);
    boolean deleteFood(Food food);
}
