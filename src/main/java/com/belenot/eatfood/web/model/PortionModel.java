package com.belenot.eatfood.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.belenot.eatfood.domain.Portion;

public class PortionModel {
    private Long id;
    private BigDecimal gram;
    private LocalDate date;
    private Long foodId;
    
    public PortionModel() {}

    private PortionModel(Portion portion) {
        this.id = portion.getId();
        this.gram = portion.getGram().setScale(2, RoundingMode.CEILING);
        this.foodId = portion.getFood().getId();
        this.date = portion.getDate();
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


    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}