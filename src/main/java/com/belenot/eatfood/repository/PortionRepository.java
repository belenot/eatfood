package com.belenot.eatfood.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.service.support.Interval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PortionRepository extends JpaRepository<Portion, Long>, JpaSpecificationExecutor<Portion> {
    List<Portion> findByFood(Food food);
    List<Portion> findByFoodId(Long foodId);
    List<Portion> findByFoodClient(Client client);
    // @Query("select p from Portion p where p.food.client = ?1 and p.time >= ?2 and p.time <= ?3")
    // List<Portion> findByDateInterval(Client client, LocalDate start, LocalDate end);
    // @Query("select p from Portion p where p.food.client = ?1 and p.time >= ?2")
    // List<Portion> findAfterDate(Client client, LocalDate start);
    // @Query("select p from Portion p where p.food.client = ?1 and p.time <= ?2")
    // List<Portion> findBeforeDate(Client client, LocalDate end);

    // List<Portion> findByFoodClientAndDateBetweenAndGramBetween(Client client, LocalDate dateStart, LocalDate dateEnd,
	// 		BigDecimal gramStart, BigDecimal gramEnd);
	// List<Portion> findByFoodClientAndGramLessThan(Client client, BigDecimal end);
	// List<Portion> findByFoodClientAndGramGreaterThan(Client client, BigDecimal start);
	// List<Portion> findByFoodClientAndGramBetween(Client client, BigDecimal start, BigDecimal end);
	// List<Portion> findByFoodClientAndDateLessThan(Client client, LocalDate end);
	// List<Portion> findByFoodClientAndDateGreaterThan(Client client, LocalDate start);
	// List<Portion> findByFoodClientAndDateBetween(Client client, LocalDate start, LocalDate end);

    

}