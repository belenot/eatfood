package com.belenot.eatfood.web.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.belenot.eatfood.domain.Food;
import com.belenot.eatfood.domain.Portion;

public class CreatePortionForm {
    private BigDecimal gram;
    private LocalDateTime time;

    private Long foodId;

    public Portion createDomain() {
        Portion portion = new Portion();
        portion.setGram(gram);
        portion.setTime(time);
        return portion;
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

    
}