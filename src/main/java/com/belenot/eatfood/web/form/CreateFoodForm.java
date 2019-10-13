package com.belenot.eatfood.web.form;

import java.math.BigDecimal;

import com.belenot.eatfood.domain.Food;

public class CreateFoodForm {
    private String name;
    private BigDecimal kcal;
    private BigDecimal prot;
    private BigDecimal carb;
    private BigDecimal fat;

    public Food createDomain() {
        Food food = new Food();
        food.setName(name);
        food.setKcal(kcal);
        food.setProt(prot);
        food.setCarb(carb);
        food.setFat(fat);
        return food;
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