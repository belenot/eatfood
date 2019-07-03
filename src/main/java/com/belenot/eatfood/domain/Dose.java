package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Dose {
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

    @Override
    public String toString() {
	String id = Integer.toString(getId());
	String food = getFood() != null ? Integer.toString(getFood().getId()) : "null";
	String date = getDate().toString();
	String str = String.format("Dose:\n\tid=%s\n\tfood=%s\n\tdate=%s\n", id, food, date);
	return str;
    }
	
}
