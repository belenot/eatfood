package com.belenot.eatfood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.repository.FoodRepository;
import com.belenot.eatfood.repository.PortionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortionService {
    @Autowired
    private PortionRepository portionRepository;
    @Autowired
    private FoodRepository foodRepository;

    public Portion createPortion(Portion portion) {
        return portionRepository.save(portion);
    }

    @Transactional
    public Portion createPortion(Portion portion, Long foodId) {
        Food food = foodRepository.findById(foodId).get();
        portion.setFood(food);
        return portionRepository.save(portion);

    }

    public boolean deletePortion(Portion portion) {
        portionRepository.delete(portion);
        return true;
    }

    public Portion updatePortion(Portion portion) {
        return portionRepository.save(portion);
    }

    @Transactional
    public Portion updatePortion(Portion portion, Long foodId) {
        Food food = foodRepository.findById(foodId).get();
        food.addPortion(portion);
        return portionRepository.save(portion);
    }

    public List<Portion> byFood(Food food) {
        return portionRepository.findByFood(food);
    }

    public List<Portion> byFoodId(Long foodId) {
        return portionRepository.findByFoodId(foodId);
    }

    public List<Portion> byClient(Client client) {
        return portionRepository.findByFoodClient(client);
    }

    public Portion byId(Long id) {
        return portionRepository.findById(id).get();
    }

    public List<Portion> byDateInterval(Client client, Optional<LocalDateTime> start, Optional<LocalDateTime> end) {
        if (start.isEmpty() && end.isEmpty()) return portionRepository.findByFoodClient(client);
        if (start.isEmpty() && end.isPresent()) return portionRepository.findBeforeDate(client, end.get());
        if (start.isPresent() && end.isEmpty()) return portionRepository.findAfterDate(client, start.get());
        if (start.isPresent() && end.isPresent()) return portionRepository.findByDateInterval(client, start.get(), end.get());
        return new ArrayList<Portion>();
    }
}