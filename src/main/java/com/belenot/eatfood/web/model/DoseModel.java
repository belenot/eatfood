package com.belenot.eatfood.web.model;

import com.belenot.eatfood.domain.Dose;

public class DoseModel {
    private int id;
    private String gram;
    private String date;
    private FoodModel foodModel;

    public DoseModel(Dose dose) {
	id = dose.getId();
	gram = dose.getGram().setScale(2).toString();
	date = dose.getDate().toString();
	foodModel = new FoodModel(dose.getFood());
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getGram() {
	return gram;
    }

    public void setGram(String gram) {
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
