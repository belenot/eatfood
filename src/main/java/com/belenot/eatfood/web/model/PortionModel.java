package com.belenot.eatfood.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.belenot.eatfood.domain.Portion;

public class PortionModel {
    private Long id;
    private BigDecimal gram;
    private LocalDate date;
    private Long foodId;

    public PortionModel(Portion portion) {
        id = portion.getId();
        gram = portion.getGram();
        date = portion.getDate();
        foodId = portion.getFood().getId();
    }

    public Long getId() {
        return id;
    }
    public BigDecimal getGram() {
        return gram;
    }
    public LocalDate getDate() {
        return date;
    }
    public Long getFoodId() {
        return foodId;
    }
}