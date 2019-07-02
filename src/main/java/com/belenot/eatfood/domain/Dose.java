package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Dose {
    private Food food;
    private Date date;
    private BigDecimal gram;
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
