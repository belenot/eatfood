package com.belenot.eatfood.service;

import java.util.List;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    
    public Food createFood(Food food) {
        food = foodRepository.save(food);
        return food;
    }

    public Food updateFood(Food food) {
        food = foodRepository.save(food);
        return food;
    }

    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}