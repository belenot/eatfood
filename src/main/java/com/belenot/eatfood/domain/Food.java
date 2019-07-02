package com.belenot.eatfood.domain;

import java.math.BigDecimal;

public class Food {
    private int id;
    private Client client;
    private String name;
    boolean common;
    private BigDecimal calories;
    private BigDecimal protein;
    private BigDecimal carbohydrate;
    private BigDecimal fat;
    
    public void setId(int id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setName(String name) { this.name = name; }
    public void setCommon(boolean common) { this.common = common; }
    public void setCalories(BigDecimal calories) { this.calories = calories; }
    public void setProtein(BigDecimal protein) { this.protein = protein; }
    public void setCarbohydrate(BigDecimal carbohydrate) { this.carbohydrate = carbohydrate; }
    public void setFat(BigDecimal fat) { this.fat = fat; }
    

    public int getId() { return id; }
    public Client getClient() { return client; }
    public String getName() { return name; }
    public boolean isCommon() { return common; }
    public BigDecimal getCalories() { return calories; }
    public BigDecimal getProtein() { return protein; }
    public BigDecimal getCarbohydrate() { return carbohydrate; }
    public BigDecimal getFat() { return fat; }

}
