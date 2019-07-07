package com.belenot.eatfood.web.model;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Food;

public class FoodModel {
    private int id;
    private String name;
    private boolean common;
    private BigDecimal calories;
    private BigDecimal protein;
    private BigDecimal carbohydrate;
    private BigDecimal fat;
    

    public FoodModel(Food food) {
	id = food.getId();
	name = food.getName();
	common = food.isCommon();
	calories = food.getCalories();
	protein = food.getProtein();
	carbohydrate = food.getCarbohydrate();
	fat = food.getFat();
    }


    public int getId() {
	return id;
    }


    public void setId(int id) {
	this.id = id;
    }


    public String getName() {
	return name;
    }


    public void setName(String name) {
	this.name = name;
    }


    public boolean isCommon() {
	return common;
    }


    public void setCommon(boolean common) {
	this.common = common;
    }


    public BigDecimal getCalories() {
	return calories;
    }


    public void setCalories(BigDecimal calories) {
	this.calories = calories;
    }


    public BigDecimal getProtein() {
	return protein;
    }


    public void setProtein(BigDecimal protein) {
	this.protein = protein;
    }


    public BigDecimal getCarbohydrate() {
	return carbohydrate;
    }


    public void setCarbohydrate(BigDecimal carbohydrate) {
	this.carbohydrate = carbohydrate;
    }


    public BigDecimal getFat() {
	return fat;
    }


    public void setFat(BigDecimal fat) {
	this.fat = fat;
    }
}
