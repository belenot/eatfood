package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Portion {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal gram;
    private LocalDate date;
    @ManyToOne
    private Food food;

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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}