package com.belenot.eatfood.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.EatfoodException.EatfoodExceptionType;
import com.belenot.eatfood.repository.ClientRepositoryMock;
import com.belenot.eatfood.service.exception.ClientSignInException;
import com.belenot.eatfood.service.exception.ClientSignUpException;
import com.belenot.eatfood.testutil.EatfoodParameterResolver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.context.SecurityContextHolder;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(EatfoodParameterResolver.class)
public class ClientServiceTest {
    private ClientService clientService;
    private Map<String, Object> registry = new HashMap<>();

    @BeforeAll
    public void init() {
        clientService = new ClientService();
        clientService.setAuthenticationManager(new AuthenticationManagerMock(clientService));
        clientService.setClientRepository(new ClientRepositoryMock());
    }

    @Test
    @Order(1)
    public void signUpTest(Client client) {
        Client client1 = assertDoesNotThrow(()->clientService.signUp(client));

        assertNotNull(client1);
        assertTrue(client1.getId() > 0, "Client id=" + client1.getId());
        assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        registry.put("client", client1);
    }

    @Test
    @Order(2)
    public void signUpExceptionTest(Client client) {
        client.setLogin(((Client)registry.get("client")).getLogin());

        assertThrows(ClientSignUpException.class, ()->clientService.signUp(client));
    }

    @Test
    @Order(3)
    public void signInTest() {
        Client client = (Client)registry.get("client");
        SecurityContextHolder.getContext().setAuthentication(null);

        Client client1 = assertDoesNotThrow(()->clientService.signIn(client.getLogin(), client.getPassword()));

        assertTrue(client1.getId() > 0, "Client id=" + client1.getId());
        assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    }

    @Test
    @Order(4)
    public void signUpTest_clientAlreadyExists() {
        Client client = (Client)registry.get("client");
        SecurityContextHolder.getContext().setAuthentication(null);

        ClientSignUpException exc = assertThrows(ClientSignUpException.class, ()->clientService.signUp(client));

        assertEquals(EatfoodExceptionType.CLIENT_ALREADY_EXISTS, exc.getType());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @Order(5)
    public void signInTest_incorrectPassword() {
        Client client = (Client)registry.get("client");
        SecurityContextHolder.getContext().setAuthentication(null);

        ClientSignInException exc = assertThrows(ClientSignInException.class, ()->clientService.signIn(client.getLogin(), client.getPassword() + "break"));

        assertEquals(EatfoodExceptionType.INCORRECT_PASSWORD, exc.getType());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @Order(5)
    public void signInTest_noSuchClient() {
        Client client = (Client)registry.get("client");
        SecurityContextHolder.getContext().setAuthentication(null);

        ClientSignInException exc = assertThrows(ClientSignInException.class, ()->clientService.signIn(client.getLogin()+"break", client.getPassword()));

        assertEquals(EatfoodExceptionType.NO_SUCH_CLIENT, exc.getType());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }


}