package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.belenot.eatfood.domain.Portion;

public class UpdatePortionForm {
    private BigDecimal gram;
    private LocalDate date;
    private Long foodId;

    public Portion updateDomain(Portion portion) {
        portion.setGram(gram);
        portion.setDate(date);
        return portion;
    }

    public void setFoodId(Long clientId) {
        this.foodId = clientId;
    }
    public Long getFoodId() {
        return foodId;
    }

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

}