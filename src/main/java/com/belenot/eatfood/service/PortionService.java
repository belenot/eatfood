package com.belenot.eatfood.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.repository.FoodRepository;
import com.belenot.eatfood.repository.PortionRepository;
import com.belenot.eatfood.repository.specification.PortionFilterSpecification;
import com.belenot.eatfood.service.support.PortionFilter;
import com.belenot.eatfood.web.model.PortionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Portion> byFilter(Client client, PortionFilter filter, Pageable page) {
        filter.setClient(client);
        return portionRepository.findAll(new PortionFilterSpecification(filter), page).getContent();
    }
}