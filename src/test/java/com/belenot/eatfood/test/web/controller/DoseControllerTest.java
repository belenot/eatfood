package com.belenot.eatfood.test.web.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.service.DoseService;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;
import com.belenot.eatfood.test.mock.service.MockDoseService;
import com.belenot.eatfood.web.controller.DoseController;

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
@ExtendWith( RandomDomainResolver.class )
public class DoseControllerTest {

    private DoseController doseController;
    private Client originClient;
    private Food originFood;
    private List<Integer> ids = new ArrayList<>();
    
    @BeforeAll
    public void init(@RandomDomain Client client, @RandomDomain Food food) {
	originClient = client;
	originFood = food;
	doseController = new DoseController();
	doseController.setDoseService(new MockDoseService());
    }

    @RepeatedTest( 10 )
    @Order( 1 )
    public void addDoseTest(@RandomDomain Dose dose) {
	dose.setFood(originFood);
	Dose addedDose = assertDoesNotThrow( () ->  doseController.addDose(dose));
	assertNotNull(addedDose);
	assertTrue(addedDose.getId() > 0);
	ids.add(addedDose.getId());
    }

    @Test
    @Order( 2 )
    public void getDoseByIdTest() {
	for (int id : ids) {
	    Dose dose = assertDoesNotThrow( () -> doseController.getDose(id));
	    assertNotNull(dose);
	}
    }

    @Test
    @Order( 3 )
    public void getDoseByFoodTest() {
	List<Dose> doses = assertDoesNotThrow( () -> doseController.getDose(originFood));
	for (int id : ids) {
	    assertTrue( doses.stream().anyMatch(d -> d.getId() == id));
	}
    }

    @Test
    @Order( 3 )
    public void getDoseByClientTest() {
	List<Dose> doses = assertDoesNotThrow( () -> doseController.getDose(originClient));
	for (int id : ids) {
	    assertTrue( doses.stream().anyMatch(d -> d.getId() == id));
	}
    }

    @Test
    @Order( 100 )
    public void deleteDoseTest() {
	for (int id : ids) {
	    Dose dose = doseController.getDose(id);
	    assertTrue(doseController.deleteDose(dose));
	    assertNull(doseController.getDose(id));
	}
    }
}

	
