package com.belenot.eatfood.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public class FoodCriteriaSql implements FoodCriteria<String> {
    private boolean desc = false;
    private int offset = 0;
    private int limit = 0;
    private String criteria = "";
    
    public FoodCriteriaSql desc(boolean desc) { this.desc = desc; return this; }
    public FoodCriteriaSql offset(int offset) { this.offset = offset; return this; }
    public FoodCriteriaSql limit(int limit) { this.limit = limit; return this; }

    @Override
    public FoodCriteriaSql moreCalories(BigDecimal calories, boolean include) {
	calories = calories.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" calories %s %s ", include ? ">=" : ">", df.format(calories));
	return this;
    }
    @Override
    public FoodCriteriaSql moreProtein(BigDecimal protein, boolean include) {
	protein = protein.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" protein %s %s ", include ? ">=" : ">", df.format(protein));
	return this;
    }
    @Override
    public FoodCriteriaSql moreCarbohydrate(BigDecimal carbohydrate, boolean include) {
	carbohydrate = carbohydrate.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" carbohydrate %s %s ", include ? ">=" : ">", df.format(carbohydrate));
	return this;
    }
    @Override
    public FoodCriteriaSql moreFat(BigDecimal fat, boolean include) {
	fat = fat.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" fat %s %s ", include ? ">=" : ">", df.format(fat));
	return this;
    }

    @Override
    public FoodCriteriaSql lessCalories(BigDecimal calories, boolean include) {
	calories = calories.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" calories %s %s ", include ? "<=" : "<", df.format(calories));
	return this;
    }
    @Override
    public FoodCriteriaSql lessProtein(BigDecimal protein, boolean include) {
	protein = protein.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" protein %s %s ", include ? "<=" : "<", df.format(protein));
	return this;
    }
    @Override
    public FoodCriteriaSql lessCarbohydrate(BigDecimal carbohydrate, boolean include) {
	carbohydrate = carbohydrate.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" carbohydrate %s %s ", include ? "<=" : "<", df.format(carbohydrate));
	return this;
    }
    @Override
    public FoodCriteriaSql lessFat(BigDecimal fat, boolean include) {
	fat = fat.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" fat %s %s ", include ? "<=" : "<", df.format(fat));
	return this;
    }

    @Override
    public FoodCriteriaSql aroundCalories(BigDecimal caloriesMin, BigDecimal caloriesMax) {
        caloriesMin = caloriesMin.setScale(2, BigDecimal.ROUND_DOWN);
	caloriesMax = caloriesMax.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" calories >= %s AND calories <= %s ", df.format(caloriesMin), df.format(caloriesMax));
	return this;
    }
    @Override
    public FoodCriteriaSql aroundProtein(BigDecimal proteinMin, BigDecimal proteinMax) {
        proteinMin = proteinMin.setScale(2, BigDecimal.ROUND_DOWN);
	proteinMax = proteinMax.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" protein >= %s AND protein <= %s ", df.format(proteinMin), df.format(proteinMax));
	return this;
    }
    @Override
    public FoodCriteriaSql aroundCarbohydrate(BigDecimal carbohydrateMin, BigDecimal carbohydrateMax) {
        carbohydrateMin = carbohydrateMin.setScale(2, BigDecimal.ROUND_DOWN);
	carbohydrateMax = carbohydrateMax.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" carbohydrate >= %s AND carbohydrate <= %s ", df.format(carbohydrateMin), df.format(carbohydrateMax));
	return this;
    }
    @Override
    public FoodCriteriaSql aroundFat(BigDecimal fatMin, BigDecimal fatMax) {
        fatMin = fatMin.setScale(2, BigDecimal.ROUND_DOWN);
	fatMax = fatMax.setScale(2, BigDecimal.ROUND_DOWN);
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(0);
	df.setGroupingUsed(false);
	criteria = String.format(" fat >= %s AND fat <= %s ", df.format(fatMin), df.format(fatMax));
	return this;
    }

    @Override
    public FoodCriteriaSql belongClient(Client client) {
	criteria = String.format(" client = %d ", client.getId());
	return this;
    }
    @Override
    public FoodCriteriaSql belongAncestor(Food ancestor) {
	criteria = String.format(" ancestor = %d ", ancestor.getId());
	return this;	
    }

    //SQL Injection Possible
    //DONT FORGET DONT FORGET DONT FORGET !!!!!!!!!!!!
    @Override
    public FoodCriteriaSql isNamed(String name) {
	criteria = String.format(" name = '%s' ", name);
	return this;
    }

    @Override
    public String criteria() {
	String suffix = "";
	suffix += desc ? " ORDER BY id DESC " : "";
	suffix += offset > 0 ? String.format(" OFFSET %d ", offset) : "";
	suffix += limit > 0 ? String.format(" LIMIT %d ", limit) : "";
	return criteria + suffix;
    }

	

}
