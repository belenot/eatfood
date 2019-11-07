package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.Map;

//@Embeddable Should I use this, if I marked field with @Embedded?
public class Nutrients {
    private BigDecimal calories;
    private BigDecimal carbohydrate;
    private BigDecimal protein;
    private BigDecimal fat;

    // What should I do with this, what kinds of nutrients there will be?
    private Map<String, BigDecimal> other;

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(BigDecimal carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public Map<String, BigDecimal> getOther() {
        return other;
    }

    public void setOther(Map<String, BigDecimal> other) {
        this.other = other;
    }

}