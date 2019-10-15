package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.belenot.eatfood.domain.Portion;

public class CreatePortionForm {
    private BigDecimal gram;
    private LocalDate date;

    private Long foodId;

    public Portion createDomain() {
        Portion portion = new Portion();
        portion.setGram(gram);
        portion.setDate(date);
        return portion;
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