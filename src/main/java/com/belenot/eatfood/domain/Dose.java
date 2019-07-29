package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Dose {
    @Id
    @GeneratedValue
    private int id;
    private BigDecimal gram;
    private Date date;
    @ManyToOne( fetch = FetchType.EAGER )
    private Food food;

    public int getId() {
	return id;
    }

    public Dose setId(int id) {
	this.id = id;
	return this;
    }

    public BigDecimal getGram() {
	return gram;
    }

    public Dose setGram(BigDecimal gram) {
	this.gram = gram;
	return this;
    }

    public Date getDate() {
	return date;
    }

    public Dose setDate(Date date) {
	this.date = date;
	return this;
    }

    public Food getFood() {
	return food;
    }

    public Dose setFood(Food food) {
	this.food = food;
	return this;
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Dose)) return false;
	Dose dose = (Dose) obj;
	return dose.getId() == getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash( getClass().getName() );
    }
}
