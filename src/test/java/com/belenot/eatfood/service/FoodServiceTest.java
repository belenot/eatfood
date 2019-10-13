package com.belenot.eatfood.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.repository.FoodRepositoryMock;
import com.belenot.eatfood.testutil.EatfoodParameterResolver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(EatfoodParameterResolver.class)
public class FoodServiceTest {
    private FoodService foodService;
    private Map<String, Object> registry = new HashMap<>();

    @BeforeAll
    public void init() {
        foodService = new FoodService();
        foodService.setFoodRepository(new FoodRepositoryMock());
    }

    @Order(1)
    @Test
    public void createFoodTest(Client client, Food food) {
        client.setId(1L);
        
        Food food1 = assertDoesNotThrow(()->foodService.createFood(client, food));

        assertNotNull(food1);
        assertTrue(food1.getId() > 0, "food1.id="+food1.getId());
        assertTrue(food1.getClient().getId() == client.getId());
        assertTrue(client.getFoods().stream().anyMatch(f->f.getId()==food.getId()));
    }

}