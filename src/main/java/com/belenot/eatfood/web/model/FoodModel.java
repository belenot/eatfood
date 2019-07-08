package com.belenot.eatfood.web.model;

import com.belenot.eatfood.domain.Food;

public class FoodModel {
    private int id;
    private String name;
    private boolean common;
    private String calories;
    private String protein;
    private String carbohydrate;
    private String fat;
    

    public FoodModel(Food food) {
	id = food.getId();
	name = food.getName();
	common = food.isCommon();
	calories = food.getCalories().setScale(2).toString();
	protein = food.getProtein().setScale(2).toString();
	carbohydrate = food.getCarbohydrate().setScale(2).toString();
	fat = food.getFat().setScale(2).toString();
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


    public String getCalories() {
	return calories;
    }


    public void setCalories(String calories) {
	this.calories = calories;
    }


    public String getProtein() {
	return protein;
    }


    public void setProtein(String protein) {
	this.protein = protein;
    }


    public String getCarbohydrate() {
	return carbohydrate;
    }


    public void setCarbohydrate(String carbohydrate) {
	this.carbohydrate = carbohydrate;
    }


    public String getFat() {
	return fat;
    }


    public void setFat(String fat) {
	this.fat = fat;
    }
}
