package com.belenot.eatfood.dao;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodCriteria<R> {
    FoodCriteria<R> moreCalories(BigDecimal calories, boolean include);
    FoodCriteria<R> moreProtein(BigDecimal protein, boolean include);
    FoodCriteria<R> moreCarbohydrate(BigDecimal carbohydrate, boolean include);
    FoodCriteria<R> moreFat(BigDecimal fat, boolean include);

    FoodCriteria<R> lessCalories(BigDecimal calories, boolean include);
    FoodCriteria<R> lessProtein(BigDecimal protein, boolean include);
    FoodCriteria<R> lessCarbohydrate(BigDecimal carbohydrate, boolean include);
    FoodCriteria<R> lessFat(BigDecimal fat, boolean include);

    FoodCriteria<R> aroundCalories(BigDecimal caloriesMin, BigDecimal caloriesMax);
    FoodCriteria<R> aroundProtein(BigDecimal proteinMin,BigDecimal proteinMax);
    FoodCriteria<R> aroundCarbohydrate(BigDecimal carbohydrateMin, BigDecimal carbohydrateMax);
    FoodCriteria<R> aroundFat(BigDecimal fatMin, BigDecimal fatMax);

    FoodCriteria<R> belongClient(Client client);
    FoodCriteria<R> belongAncestor(Food ancestor);
    FoodCriteria<R> isNamed(String name);

    R criteria();
    
}
	
