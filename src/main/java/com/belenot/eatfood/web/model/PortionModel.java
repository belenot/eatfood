package com.belenot.eatfood.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import com.belenot.eatfood.domain.Portion;

public class PortionModel {
    private Long id;
    private BigDecimal gram;
    private LocalDateTime time;
    private Long foodId;
    private String foodName;
    
    public PortionModel() {}

    private PortionModel(Portion portion) {
        this.id = portion.getId();
        this.gram = portion.getGram().setScale(2, RoundingMode.CEILING);
        this.foodId = portion.getFood().getId();
        this.time = portion.getTime();
    }

    public static PortionModel of(Portion portion) {
        return new PortionModel(portion);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGram() {
        return gram;
    }

    public void setGram(BigDecimal gram) {
        this.gram = gram;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}