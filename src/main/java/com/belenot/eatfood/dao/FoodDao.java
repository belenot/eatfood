package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.exception.ApplicationException;

public interface FoodDao {
    void init() throws ApplicationException;
    void destroy() throws ApplicationException;
    
    Food getFood(int id) throws ApplicationException;
    List<Food> getFoodByName(String name) throws ApplicationException;
    List<Food> getFoodByClient(Client client) throws ApplicationException;
    Food addFood(String name, Client client) throws ApplicationException;
    Food updateFood(Food food) throws ApplicationException;
    boolean deleteFood(Food food) throws ApplicationException;
}
