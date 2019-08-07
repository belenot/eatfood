package com.belenot.eatfood.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
public class Client {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "client-id-seq-generator" )
    @SequenceGenerator( name = "client-id-seq-generator", sequenceName = "client_id_seq", allocationSize = 1 )
    private int id;
    @NaturalId( mutable = true )
    private String login;
    private String password;
    @Embedded
    private ClientData data = new ClientData();
    @OneToMany( mappedBy = "client", cascade = { CascadeType.REMOVE }, orphanRemoval = true )
    private List<Food> foods =  new ArrayList<>();

    public Client addFood(Food food) {
	foods.add(food);
	food.setClient(this);
	return this;
    }

    public Client removeFood(Food food) {
	foods.remove(food);
	food.setClient(null);
	return this;
    }

    public int getId() {
	return id;
    }

    public Client setId(int id) {
	this.id = id;
	return this;
    }

    public String getLogin() {
	return login;
    }

    public Client setLogin(String login) {
	this.login = login;
	return this;
    }

    public String getPassword() {
	return password;
    }

    public Client setPassword(String password) {
	this.password = password;
	return this;
    }

    public ClientData getData() {
	return data;
    }

    public Client setData(ClientData data) {
	this.data = data;
	return this;
    }

    public List<Food> getFoods() {
	return foods;
    }

    public Client setFoods(List<Food> foods) {
	this.foods = foods;
	return this;
    }
}
