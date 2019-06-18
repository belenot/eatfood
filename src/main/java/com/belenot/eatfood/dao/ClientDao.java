package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;

public interface ClientDao {
    void init() throws ApplicationException;
    void destroy() throws ApplicationException;
    
    Client getClient(int id) throws ApplicationException;
    List<Client> getClientByLogin(String login) throws ApplicationException;
    Client addClient(String login) throws ApplicationException;
    Client updateClient(Client client) throws ApplicationException;
    boolean deleteClient(Client client) throws ApplicationException;
    
    
}
