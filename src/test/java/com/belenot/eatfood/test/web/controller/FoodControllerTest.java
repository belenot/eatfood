package com.belenot.eatfood.test.web.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.FoodService;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver.EatfoodService;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;
import com.belenot.eatfood.test.mock.service.MockFoodService;
import com.belenot.eatfood.web.controller.FoodController;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@TestMethodOrder( OrderAnnotation.class )
@TestInstance( Lifecycle.PER_CLASS )
@ExtendWith( { EatfoodServiceResolver.class, RandomDomainResolver.class } )
public class FoodControllerTest {

    private FoodController foodController;
    private List<Integer> ids = new ArrayList<>();
    private Client originClient;

    @BeforeAll
    public void setFoodController(@RandomDomain Client client,
				  @EatfoodService FoodController foodController) {
	originClient = client;
	originClient.setId(1);
	this.foodController = foodController;
	FoodService foodSerivce = new MockFoodService();
	foodController.setFoodService(foodSerivce);
    }

    @RepeatedTest( 10 )
    @Order( 1 )
    public void addFoodTest(@RandomDomain Food food) {
        Food addedFood = assertDoesNotThrow( () -> foodController.addFood(food, originClient));
	ids.add(addedFood.getId());
	assertNotNull(addedFood);
	assertTrue(addedFood.getId() > 0);
    }

    @Test
    @Order( 2 )
    public void getFoodByIdTest() {
	for (int id : ids) {
	    Food food = assertDoesNotThrow( () -> foodController.getFood(id));
	    assertNotNull(food);
	}
    }

    @Test
    @Order( 3 )
    public void getFoodByClientTest() {
	List<Food> foods = assertDoesNotThrow( () -> foodController.getFood(originClient));
	for (int id : ids) {
	    assertTrue(foods.stream().anyMatch( f -> f.getId() == id));
	}
    }

    @Test
    @Order( 4 )
    public void updateFoodTest() {
	Food food = foodController.getFood(ids.get(0));
	String originName = food.getName();
	food.setName("anotherName");
	/** No matter which client is */
        Food assertedFood = assertDoesNotThrow( () -> foodController.updateFood(null, food));
	assertEquals(assertedFood.getId(), food.getId());
	assertEquals(assertedFood.getName(), "anotherName");
	assertNotEquals(assertedFood.getName(), originName);
    }

    @Test
    @Order( 100 )
    public void deleteFood() {
	for (int id : ids) {
	    boolean result = assertDoesNotThrow( () -> foodController.deleteFood(id));
	    assertTrue(result);
	}
    }
    
}
