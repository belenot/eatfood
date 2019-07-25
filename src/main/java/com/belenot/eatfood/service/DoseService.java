package com.belenot.eatfood.service;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Dose;
import com.belenot.eatfood.domain.Food;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DoseService {

    @Autowired
    private SessionFactory sessionFactory;

    public DoseService setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	return this;
    }
    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    @Transactional
    public void addDose(Food food, Dose dose) {
	Session session = sessionFactory.getCurrentSession();
	session.lock(food, LockMode.NONE);
	food.addDose(dose);
	session.save(dose);
    }

    @Transactional
    public Dose getDoseById(int id) {
	Session session = sessionFactory.getCurrentSession();
	Dose dose = session.byId(Dose.class).load(id);
	return dose;
    }

    @Transactional
    public List<Dose> getDoseByFood(Food food) {
	Session session = sessionFactory.getCurrentSession();
	session.lock(food, LockMode.NONE);
	List<Dose> doses = new ArrayList<>();
	doses.addAll(food.getDoses());
	return doses;
    }

    @Transactional
    public List<Dose> getDoseByClient(Client client) {
	Session session = sessionFactory.getCurrentSession();
	session.lock(client, LockMode.NONE);
	List<Dose> doses = new ArrayList<>();
	doses.addAll(session.createQuery("select d from Dose d " + 
					 "inner join Food f on d.food = f.id " +
					 "inner join Client c on f.client = c.id " +
					 "where c.id = :id", Dose.class).setParameter("id", client.getId()).list());
	return doses;	
    }

    @Transactional
    public void deleteDose(Dose dose) {
	Session session = sessionFactory.getCurrentSession();
	session.lock(dose, LockMode.NONE);
	dose.getFood().removeDose(dose);
	session.delete(dose);
    }
    
    
}

    
