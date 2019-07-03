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

    @Override
    public String toString() {
        String id = Integer.toString(getId());
        String client = getClient() != null? Integer.toString(getClient().getId()) : "null";
	String name = getName();
        String common = Boolean.toString(isCommon());
	String calories = getCalories() != null ? Double.toString(getCalories().doubleValue()) : "null";
	String protein = getProtein() != null ? Double.toString(getProtein().doubleValue()) : "null";
	String carbohydrate = getCarbohydrate() != null ? Double.toString(getCarbohydrate().doubleValue()) : "null";
	String fat = getFat() != null ? Double.toString(getFat().doubleValue()) : "null";
	String str = String.format("Food:\n\tid=%s\n\tclient=%s\n\tname=%s\n\tcommon=%s\n\tcalories=%s\n\tprotein=%s\n\tcarbohydrate=%s\n\tfat=%s\n",
				   id, client, name, common, calories, protein, carbohydrate, fat);
	return str;
    }

}
