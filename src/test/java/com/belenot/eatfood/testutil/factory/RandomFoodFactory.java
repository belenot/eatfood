package com.belenot.eatfood.testutil.factory;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Food;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomFoodFactory implements RandomDomainFactory<Food> {
    public Food generate() {
        Food food = new Food();
        food.setName(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
        food.setKcal(new BigDecimal(RandomUtils.nextInt(0, 2000)));
        food.setProt(new BigDecimal(RandomUtils.nextInt(0, 500)));
        food.setCarb(new BigDecimal(RandomUtils.nextInt(0, 500)));
        food.setFat(new BigDecimal(RandomUtils.nextInt(0, 500)));
        return food;
    }
}