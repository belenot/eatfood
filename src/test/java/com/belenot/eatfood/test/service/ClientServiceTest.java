package com.belenot.eatfood.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.belenot.eatfood.TestUtil;
import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.ClientService;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;


@TestInstance( Lifecycle.PER_CLASS )
@TestMethodOrder( OrderAnnotation.class )
@ExtendWith( { EatfoodServiceResolver.class, RandomDomainResolver.class } )
public class ClientServiceTest {
    private ClientService clientService;
    private Logger logger = LogManager.getLogger(this.getClass());

    private Client originClient = null;

    @BeforeAll
    public void init(TestInfo info, @EatfoodService ClientService clientService, @RandomDomain Client client) {       
	this.clientService = clientService;
        originClient = client;
    }

    @BeforeEach
    public void logBefore(TestInfo info, TestReporter reporter) {
	logger.info("@|blue,bold " + info.getDisplayName() + "|@");
    }

    @Test
    @Order( 1 )
    public void addClientTest() {
	Client client = TestUtil.copyClient(originClient);
	assertDoesNotThrow( () -> clientService.addClient(client));
	originClient.setId(client.getId());
    }

    @Test
    @Order( 2 )
    public void getClientByIdTest() {
	Client client = clientService.getClientById(originClient.getId());
	assertNotNull(client);
	assertEquals(client.getId(), originClient.getId());
	assertEquals(client.getLogin(), originClient.getLogin());
    }

    @Test
    @Order( 3 )
    public void getClientByLoginTest() {
	Client client = assertDoesNotThrow( () -> clientService.getClientByLogin(originClient.getLogin()));
	assertNotNull(client);
	assertEquals(client.getId(), originClient.getId());
	assertEquals(client.getLogin(), originClient.getLogin());
    }

    @Test
    @Order( 4 )
    public void validedClientTest() {
	Client client = clientService.getClientById(originClient.getId());
	assertEquals(client.getId(), originClient.getId());
	assertEquals(client.getLogin(), originClient.getLogin());
	assertEquals(client.getPassword(), originClient.getPassword());
	assertEquals(client.getData().getName(), originClient.getData().getName());
	assertEquals(client.getData().getSurname(), originClient.getData().getSurname());
	assertEquals(client.getData().getEmail(), originClient.getData().getEmail());
    }
    
    @Test
    @Order( 100 )
    public void deleteClientTest() {
	assertDoesNotThrow( () -> clientService.deleteClient(clientService.getClientById(originClient.getId())));
	assertNull(clientService.getClientById(originClient.getId()));
    }
}
