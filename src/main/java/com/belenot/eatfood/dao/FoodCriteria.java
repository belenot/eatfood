package com.belenot.eatfood.dao;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Food;

public interface FoodCriteria<R> {
    R moreCalories(BigDecimal calories, boolean include);
    R moreProtein(BigDecimal protein, boolean include);
    R moreCarbohydrate(BigDecimal carbohydrate, boolean include);
    R moreFat(BigDecimal fat, boolean include);

    R lessCalories(BigDecimal calories, boolean include);
    R lessProtein(BigDecimal protein, boolean include);
    R lessCarbohydrate(BigDecimal carbohydrate, boolean include);
    R lessFat(BigDecimal fat, boolean include);

    R aroundCalories(BigDecimal caloriesMin, BigDecimal caloriesMax);
    R aroundProtein(BigDecimal proteinMin,BigDecimal proteinMax);
    R aroundCarbohydrate(BigDecimal carbohydrateMin, BigDecimal carbohydrateMax);
    R aroundFat(BigDecimal fatMin, BigDecimal fatMax);

    R belongClient(Client client);
    R belongAncestor(Food ancestor);
    R isNamed(String name);
    
}
	
