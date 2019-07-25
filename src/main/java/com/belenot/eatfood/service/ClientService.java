package com.belenot.eatfood.service;

import com.belenot.eatfood.domain.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClientService {
    @Autowired
    private SessionFactory sessionFactory;
    public ClientService setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	return this;
    }
    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    @Transactional
    public void addClient(Client client) {
	Session session = sessionFactory.getCurrentSession();
	session.save(client);
    }

    @Transactional
    public Client getClientById(int id) {
	Session session = sessionFactory.getCurrentSession();
	Client client = session.byId(Client.class).load(id);
	return client;
    }

    @Transactional
    public Client getClientByLogin(String login) {
	Session session = sessionFactory.getCurrentSession();
	Client client = session.bySimpleNaturalId(Client.class).load(login);
	return client;       
    }

    @Transactional
    public void deleteClient(Client client) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(client);
    }
}
