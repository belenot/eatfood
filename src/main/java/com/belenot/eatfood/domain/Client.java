package com.belenot.eatfood.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String login;
    private String name;
    private String password;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void addFood(Food food) {
        this.foods.add(food);
        food.setClient(this);
    }
    

}