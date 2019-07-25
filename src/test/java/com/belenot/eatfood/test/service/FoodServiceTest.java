package com.belenot.eatfood.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver.EatfoodService;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@TestMethodOrder( OrderAnnotation.class )
@TestInstance( Lifecycle.PER_CLASS )
@ExtendWith( { EatfoodServiceResolver.class, RandomDomainResolver.class } )
public class FoodServiceTest {
    private Logger logger = LogManager.getLogger(this.getClass());

    private FoodService foodService;
    private ClientService clientService;

    private Client originClient;
    private List<Integer> ids = new ArrayList<>();   

    @BeforeAll
    public void init(@EatfoodService FoodService foodService, @EatfoodService ClientService clientService, @RandomDomain Client client) {
	this.foodService = foodService;
	this.clientService = clientService;
        originClient = client;
	clientService.addClient(originClient);
    }
    @BeforeEach
    public void logBefore(TestInfo info) {
	logger.info("@|blue,bold " + info.getDisplayName() + "|@");
    }

    @RepeatedTest( 10 )
    @Order( 1 )
    public void addFoodTest(@RandomDomain Food food) {
	Client client = clientService.getClientById(originClient.getId());
	assertDoesNotThrow( () -> foodService.addFood(client, food));
	assertNotNull(foodService.getFoodById(food.getId()));
	ids.add(food.getId());
    }

    @Test
    @Order( 2 )
    public void getFoodByIdTest() {
	for (Integer id : ids) {
	    Food food = assertDoesNotThrow( () -> foodService.getFoodById(id));
	    assertNotNull(food);
	}
    }

    @Test
    @Order( 100 )
    public void deleteFoodTest() {
	for (Integer id : ids) {
	    assertNotNull(foodService.getFoodById(id));
	    Food food = foodService.getFoodById(id);
	    foodService.deleteFood(food);
	    originClient = clientService.getClientById(originClient.getId());
	    assertNull(foodService.getFoodById(id));
	}
	
    }
}
