package com.belenot.eatfood.dao;

import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;

public interface ClientDao {
    Client getClientById(int id) throws Exception;
    Client getClientByLogin(String login, String password) throws Exception;
    Client addClient(String login, String password, String name, String surname, String email) throws Exception;
    void updateClient(Client client) throws Exception;
    boolean deleteClient(Client client) throws Exception;    
}
