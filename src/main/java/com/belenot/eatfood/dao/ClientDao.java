package com.belenot.eatfood.dao;

import com.belenot.eatfood.domain.Client;

public interface ClientDao {
    Client getClientById(int id) throws Exception;
    Client getClientByLogin(String login, String password) throws Exception;
    Client addClient(Client client) throws Exception;
    void updateClient(Client client) throws Exception;
    boolean deleteClient(Client client) throws Exception;    
}
