package com.belenot.eatfood.web.model;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Food;

public class FoodModel {
    private Long id;
    private String name;
    private BigDecimal kcal;
    private BigDecimal prot;
    private BigDecimal carb;
    private BigDecimal fat;

    public FoodModel() {}
    
    private FoodModel(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.kcal = food.getKcal();
        this.prot = food.getProt();
        this.carb = food.getCarb();
        this.fat = food.getFat();
    }

    public static FoodModel of(Food food) {
        return new FoodModel(food);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public BigDecimal getProt() {
        return prot;
    }

    public void setProt(BigDecimal prot) {
        this.prot = prot;
    }

    public BigDecimal getCarb() {
        return carb;
    }

    public void setCarb(BigDecimal carb) {
        this.carb = carb;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }
}