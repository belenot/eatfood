package com.belenot.eatfood.test.mock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.FoodService;

public class MockFoodService extends FoodService {
    private Map<Food, Client> foods = new HashMap<>();
    private int id = 1;

    @Override
    public void addFood(Client client, Food food) {
	food.setId(id++);
	foods.put(food, client);
    }

    @Override
    public Food getFoodById(int id) {
	return foods.keySet().stream().filter( f -> f.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Food> getFoodByClient(Client client) {
	List<Food> result = new ArrayList<>();
	for (Food food : foods.keySet()) {
	    if (foods.get(food).getId() == client.getId()) {
		result.add(food);
	    }
	}
	return result;
    }

    //Only for field NAME
    @Override
    public void updateFood(Food food) {
	Food updatedFood = foods.keySet().stream().filter( f -> f.getId() == food.getId()).findFirst().orElse(null);
	updatedFood.setName(food.getName());
    }
    
    @Override
    public void deleteFood(Food food) {
        foods.remove(food);
    }
    
}
