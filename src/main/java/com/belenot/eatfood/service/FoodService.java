package com.belenot.eatfood.service;

import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FoodService {
    @Autowired
    private SessionFactory sessionFactory;
    public FoodService setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	return this;
    }
    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    @Transactional
    public void addFood(Client client, Food food) {
	Session session = sessionFactory.getCurrentSession();
	client = session.byId(Client.class).load(client.getId());
	client.addFood(food);
	session.save(food);
    }

    @Transactional
    public Food getFoodById(int id) {
	Session session = sessionFactory.getCurrentSession();
	Food food = session.byId(Food.class).load(id);
	return food;
    }

    @Transactional
    public List<Food> getFoodByClient(Client client) {
	List<Food> foods = new ArrayList<>();
	Session session = sessionFactory.getCurrentSession();
	client = session.byId(Client.class).load(client.getId());
	foods.addAll(client.getFoods());
	return foods;
    }

    @Transactional
    public void deleteFood(Food food) {
	Session session = sessionFactory.getCurrentSession();
        food = session.byId(Food.class).load(food.getId());
	food.getClient().removeFood(food);
	session.delete(food);       
    }
}
