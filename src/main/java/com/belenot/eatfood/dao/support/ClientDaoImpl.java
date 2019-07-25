package com.belenot.eatfood.dao.support;

import com.belenot.eatfood.dao.ClientDao;
import com.belenot.eatfood.domain.Client;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientDaoImpl implements ClientDao {


    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
    
    @Override
    public Client addClient(Client client) throws Exception {
	return null;
    }

    @Override
    public boolean deleteClient(Client client) throws Exception {
	return false;
    }

    @Override
    public Client getClientById(int id) throws Exception {
	return null;
    }

    @Override
    public Client getClientByLogin(String login, String password) throws Exception {
	return null;
    }

    @Override
    public void updateClient(Client client) throws Exception {
		
    }
}    
