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

    public Food updateFood(Long id, Food food) {
        Food old = foodRepository.findById(id).get();
        old.setName(food.getName());
        old.setNutrients(food.getNutrients());
        old.setParent(food.getParent());
        food = foodRepository.save(old);
        return food;
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getById(Long id) {
        if (id == null) return null;
        return foodRepository.findById(id).orElse(null);
    }
}