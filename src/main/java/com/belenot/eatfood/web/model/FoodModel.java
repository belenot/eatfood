package com.belenot.eatfood.web.model;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Nutrients;

public class FoodModel {
    private Long id;
    private String name;
    private Long authorId;
    private Long parentId;
    private Nutrients nutrients;

    public FoodModel(Food food) {
        id = food.getId();
        name = food.getName();
        authorId = food.getAuthor().getId();
        if (food.getParent() != null) {
            parentId = food.getParent().getId();
        }
        nutrients = food.getNutrients();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Long getAuthorId() {
        return authorId;
    }
    public Long getParentId() {
        return parentId;
    }
    public Nutrients getNutrients() {
        return nutrients;
    }
}