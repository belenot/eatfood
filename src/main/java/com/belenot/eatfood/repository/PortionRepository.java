package com.belenot.eatfood.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PortionRepository extends JpaRepository<Portion, Long> {
    List<Portion> findByFood(Food food);
    List<Portion> findByFoodId(Long foodId);
    List<Portion> findByFoodClient(Client client);
    @Query("select p from Portion p where p.food.client = ?1 and p.time >= ?2 and p.time <= ?3")
    List<Portion> findByDateInterval(Client client, LocalDateTime start, LocalDateTime end);
    @Query("select p from Portion p where p.food.client = ?1 and p.time >= ?2")
    List<Portion> findAfterDate(Client client, LocalDateTime start);
    @Query("select p from Portion p where p.food.client = ?1 and p.time <= ?2")
    List<Portion> findBeforeDate(Client client, LocalDateTime end);
}