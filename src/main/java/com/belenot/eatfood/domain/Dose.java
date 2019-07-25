package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dose {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    private Food food;
    private Date date;
    private BigDecimal gram;
    public int getId() {
	return id;
    }
    public void setId(int id) {
	this.id = id;
    }
    public Food getFood() {
	return food;
    }
    public void setFood(Food food) {
	this.food = food;
    }
    public Date getDate() {
	return date;
    }
    public void setDate(Date date) {
	this.date = date;
    }
    public BigDecimal getGram() {
	return gram;
    }
    public void setGram(BigDecimal gram) {
	this.gram = gram;
    }	
}
