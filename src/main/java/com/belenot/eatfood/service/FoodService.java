package com.belenot.eatfood.service;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Transactional
    public Food createFood(Client client, Food food) {
        foodRepository.save(food);
        client.addFood(food);
        foodRepository.save(food);
        return food;
    }

    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }

    public boolean deleteFood(Food food) {
        foodRepository.delete(food);
        return true;
    }

    public List<Food> byClient(Client client) {
        return foodRepository.findByClient(client);
    }

    public Food byId(Long id) {
        return foodRepository.findById(id).get();
    }
}

