package com.belenot.eatfood.dao;

import java.sql.SQLException;
import java.util.List;

import com.belenot.eatfood.domain.Client;

public interface ClientDao {
    void init() throws Exception;
    void destroy() throws Exception;
    
    Client getClient(int id);
    List<Client> getClientByLogin(String login);
    Client addClient(String login);
    Client updateClient(Client client);
    boolean deleteClient(Client client);
    
    
}
