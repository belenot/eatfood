package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;


@Entity

public class Dose {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.CEILING;
    public static final int SCALE = 2;
    
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "dose-id-seq-generator" )
    @SequenceGenerator( name = "dose-id-seq-generator", sequenceName = "dose_id_seq", allocationSize = 1 )
    private int id;
    private BigDecimal gram;
    private Date date;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn( name = "food" )
    private Food food;

    public int getId() {
	return id;
    }

    public Dose setId(int id) {
	this.id = id;
	return this;
    }

    public BigDecimal getGram() {
	return gram.setScale(SCALE, ROUNDING_MODE);
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
