package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdatePortionForm {
    private BigDecimal gram;
    private LocalDate date;
    private Long foodId;

    public BigDecimal getGram() {
        return gram;
    }

    public void setGram(BigDecimal gram) {
        this.gram = gram;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }
}