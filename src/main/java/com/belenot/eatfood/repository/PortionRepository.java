package com.belenot.eatfood.repository;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortionRepository extends JpaRepository<Portion, Long> {
    List<Portion> findByFood(Food food);
    List<Portion> findByFoodId(Long foodId);
    List<Portion> findByFoodClient(Client client);
}