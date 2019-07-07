package com.belenot.eatfood.web.model;

import java.math.BigDecimal;
import java.util.Date;

import com.belenot.eatfood.domain.Dose;

public class DoseModel {
    private int id;
    private BigDecimal gram;
    private String date;
    private FoodModel foodModel;

    public DoseModel(Dose dose) {
	id = dose.getId();
	gram = dose.getGram();
	date = dose.getDate().toString();
	foodModel = new FoodModel(dose.getFood());
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public BigDecimal getGram() {
	return gram;
    }

    public void setGram(BigDecimal gram) {
	this.gram = gram;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public FoodModel getFoodModel() {
	return foodModel;
    }

    public void setFoodModel(FoodModel foodModel) {
	this.foodModel = foodModel;
    }
}
