package com.belenot.eatfood.test.mock.service;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.AuthorizationService;

import org.hibernate.HibernateException;

public class MockAuthorizationService extends AuthorizationService {
    List<Client> clients = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Client registerClient(Client clientData) {
	if (clients.stream().anyMatch( c -> c.getLogin().equals(clientData.getLogin()))) {
	    throw new HibernateException("Test: client with such login already exists");
	}
	clientData.setId(currentId++);
	clients.add(clientData);
	return clientData;
    }
    @Override
    public Client authorizeClient(String login, String password) {
        return clients.stream().filter( c -> c.getLogin().equals(login) && c.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public boolean isValidClient(Client client) {
        return clients.stream().anyMatch( c -> c.getId() == client.getId());
    }
}
