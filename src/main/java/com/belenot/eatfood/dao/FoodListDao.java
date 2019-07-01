package com.belenot.eatfood.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.belenot.eatfood.domain.FoodList;

public interface FoodListDao {
    FoodList newFoodList(FoodList foodList) throws Exception;
    FoodList updateFoodList(FoodList foodList) throws Exception;
    FoodList getFoodListById(int id) throws Exception;
    List<FoodList> getFoodListAfter(Date day, int offset, int limit) throws Exception;
    List<FoodList> getFoodListBefore(Date day, int offset, int limit) throws Exception;
    List<FoodList> getFoodListBetween(Date dayMin, Date dayMax) throws Exception;
}
