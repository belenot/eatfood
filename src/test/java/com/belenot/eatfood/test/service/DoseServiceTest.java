package com.belenot.eatfood.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.service.DoseService;
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
public class DoseServiceTest {
    private Logger logger = LogManager.getLogger(this.getClass());
    private DoseService doseService;
    private FoodService foodService;
    private ClientService clientService;
    private Client originClient;
    private Food originFood;
    private List<Integer> ids = new ArrayList<>(10);
    
    @BeforeAll
    public void init(@EatfoodService DoseService doseService, @EatfoodService FoodService foodService,
		     @EatfoodService ClientService clientService, @RandomDomain Client client, @RandomDomain Food food) {
	this.doseService = doseService;
	this.foodService = foodService;
	this.clientService = clientService;
        originClient = client;
	clientService.addClient(originClient);
        originFood = food;
	foodService.addFood(originClient, originFood);
    }
    @BeforeEach
    public void logBefore(TestInfo info) {
	logger.info("@|blue,bold " + info.getDisplayName() + "|@");
    }

    @RepeatedTest( 10 )
    @Order( 1 )
    public void addDoseTest(@RandomDomain Dose dose) {
	Food food = foodService.getFoodById(originFood.getId());
	assertDoesNotThrow( () -> doseService.addDose(food, dose));
	assertNotNull( doseService.getDoseById(dose.getId()));
	ids.add(dose.getId());
    }

    @Test
    @Order( 2 )
    public void getDoseByIdTest() {
	for (int id : ids) {
	    assertNotNull(doseService.getDoseById(id));
	}
    }

    @Test
    @Order( 3 )
    public void getDoseByFoodTest() {
	Food food = foodService.getFoodById(originFood.getId());
	List<Dose> doses = doseService.getDoseByFood(food);
	for (Dose dose : doses) {
	    assertTrue(ids.stream().anyMatch( (id) -> id == dose.getId()));
	}
    }

    @Test
    @Order( 3 )
    public void getDoseByClientTest() {
	Client client = clientService.getClientById(originClient.getId());
	List<Dose> doses = doseService.getDoseByClient(client);
	assertTrue(doses.size() > 0);
	for (Dose dose : doses) {
	    assertNotNull(dose);
	    Food food = foodService.getFoodById(dose.getFood().getId());
	    assertNotNull(food);
	    assertEquals(client.getId(), food.getClient().getId());
	}
    }

    @Test
    @Order( 100 )
    public void deleteDoseTest() {
	for (int id : ids) {
	    Dose dose = doseService.getDoseById(id);
	    assertNotNull(dose);
	    assertDoesNotThrow( () -> doseService.deleteDose(dose));
	    assertNull(doseService.getDoseById(id));
	}
    }
}
