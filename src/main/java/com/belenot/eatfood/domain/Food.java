package com.belenot.eatfood.domain;

public class Food {
    private long id;
    private Client client;
    private String name;
    private double calories;
    private double protein;
    private double carbohydrate;
    private double fat;

    public void setId(long id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setName(String name) { this.name = name; }
    public void setCalories(double calories) { this.calories = calories; }
    public void setProtein(double protein) { this.protein = protein; }
    public void setCarbohydrate(double carbohydrate) { this.carbohydrate = carbohydrate; }
    public void setFat(double fat) { this.fat = fat; }

    public long getId() { return id; }
    public Client getClient() { return client; }
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public double getProtein() { return protein; }
    public double getCarbohydrate() { return carbohydrate; }
    public double getFat() { return fat; }
}
