package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;

public interface ClientDao {
    Client getClient(long id);
    List<Client> getClientByLogin(String login);
    Client addClient(String login);
    Client updateClient(Client client);
    boolean deleteClient(Client client);
    
}
