package com.belenot.eatfood.repository;

import com.belenot.eatfood.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    
}