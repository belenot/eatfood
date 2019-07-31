package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

@Embeddable
public class Nutrients {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;
    public static final int SCALE = 2;
    
    private BigDecimal calories;
    private BigDecimal protein;
    private BigDecimal carbohydrate;
    private BigDecimal fat;

    public Nutrients() { }
    public Nutrients(double calories, double protein, double carbohydrate, double fat) {
	this.calories = new BigDecimal(calories).setScale(2, RoundingMode.CEILING);
	this.protein = new BigDecimal(protein).setScale(2, RoundingMode.CEILING);
	this.carbohydrate = new BigDecimal(carbohydrate).setScale(2, RoundingMode.CEILING);
	this.fat = new BigDecimal(fat).setScale(2, RoundingMode.CEILING);
    }
    
    public BigDecimal getCalories() {
	return calories.setScale(SCALE, ROUNDING_MODE);
    }
    public Nutrients setCalories(BigDecimal calories) {
	this.calories = calories;
	return this;
    }
    public BigDecimal getProtein() {
	return protein.setScale(SCALE, ROUNDING_MODE);
    }
    public Nutrients setProtein(BigDecimal protein) {
	this.protein = protein;
	return this;
    }
    public BigDecimal getCarbohydrate() {
	return carbohydrate.setScale(SCALE, ROUNDING_MODE);
    }
    public Nutrients setCarbohydrate(BigDecimal carbohydrate) {
	this.carbohydrate = carbohydrate;
	return this;
    }
    public BigDecimal getFat() {
	return fat.setScale(SCALE, ROUNDING_MODE);
    }
    public Nutrients setFat(BigDecimal fat) {
	this.fat = fat;
	return this;
    }
}
