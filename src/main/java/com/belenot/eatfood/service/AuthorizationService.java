package com.belenot.eatfood.service;

import com.belenot.eatfood.domain.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private ClientService clientService;
    public void setClientService(ClientService clientService) { this.clientService = clientService; }

    public Client authorizeClient(String login, String password) {
	Client client = clientService.getClientByLogin(login);
	if (client == null) return null;
	if (client.getPassword().equals(password)) return client;
	return null;
    }
    //No tests. Facade on clientService
    public Client registerClient(Client clientData) {
	clientService.addClient(clientData);
	return clientService.getClientById(clientData.getId());
    }

    //No tests. Delegates responsability to HtppSession.
    public boolean isValidClient(Client client) {
	client = clientService.getClientById(client.getId());
	return client != null;
    }
    
}
