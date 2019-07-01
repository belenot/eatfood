package com.belenot.eatfood.dao;

import java.sql.SQLException;

import com.belenot.eatfood.domain.Account;
import com.belenot.eatfood.domain.Client;

public interface ClientDao {
    Client getClientById(int id) throws Exception;
    Client getClientByAccount(Account account) throws Exception;
    Client newClient(Client client, Account account) throws Exception;
    Client updateClient(Client client) throws Exception;
    Client deleteClient(Client client) throws Exception;
    
    
}
