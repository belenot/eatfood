package com.belenot.eatfood.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Food {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal kcal;
    private BigDecimal prot;
    private BigDecimal carb;
    private BigDecimal fat;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "food", orphanRemoval = true)
    private List<Portion> portions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public BigDecimal getProt() {
        return prot;
    }

    public void setProt(BigDecimal prot) {
        this.prot = prot;
    }

    public BigDecimal getCarb() {
        return carb;
    }

    public void setCarb(BigDecimal carb) {
        this.carb = carb;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public void addPortion(Portion portion) {
        this.portions.add(portion);
        portion.setFood(this);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    

}