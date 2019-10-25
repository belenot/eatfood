package com.belenot.eatfood.repository;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByNameAndClient(String name, Client client);
    List<Food> findByClient(Client client);
}
