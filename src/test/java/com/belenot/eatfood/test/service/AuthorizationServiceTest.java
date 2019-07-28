package com.belenot.eatfood.test.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.AuthorizationService;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver;
import com.belenot.eatfood.test.extension.RandomDomainResolver;
import com.belenot.eatfood.test.extension.EatfoodServiceResolver.EatfoodService;
import com.belenot.eatfood.test.extension.RandomDomainResolver.RandomDomain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder( OrderAnnotation.class )
@TestInstance( Lifecycle.PER_CLASS )
@ExtendWith( { EatfoodServiceResolver.class, RandomDomainResolver.class } )
public class AuthorizationServiceTest {

    private AuthorizationService authorizationService;
    private Client originClient;
    
    @BeforeAll
    public void init(@EatfoodService AuthorizationService authorizationService,
		     @RandomDomain Client client) {
	originClient = client;
	originClient.setId(1);
	this.authorizationService = authorizationService;
	ClientService clientService = mock(ClientService.class);
	when(clientService.getClientByLogin(anyString()))
	    .then( p -> p.getArgument(0).equals(client.getLogin()) ? client : null);
	when(clientService.getClientById(anyInt()))
	    .then( p -> p.getArgument(0, Integer.class) == client.getId() ? client : null);
	authorizationService.setClientService(clientService);
    }

    @Test
    @Order( 1 )
    public void authorizeClientTest(@RandomDomain Client otherClient) {
	Client client = assertDoesNotThrow( () -> authorizationService.authorizeClient(originClient.getLogin(), originClient.getPassword()));
	assertNotNull(client);
	assertEquals(client.getId(), originClient.getId());
    }
    
}
