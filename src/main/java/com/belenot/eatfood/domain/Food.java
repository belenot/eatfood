package com.belenot.eatfood.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Food {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "food-id-seq-generator" )
    @SequenceGenerator( name = "food-id-seq-generator", sequenceName = "food_id_seq", allocationSize = 1 )
    private int id;
    private String name;
    private Nutrients nutrients;
    //@ManyToOne
    //@JoinColumn( name = "ancestor" )
    //private Food defaultFood;
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "client" )
    @JsonIgnore
    private Client client;
    @OneToMany( mappedBy = "food", cascade = CascadeType.REMOVE, orphanRemoval = true )
    @JsonIgnore
    private List<Dose> doses;

    public Food addDose(Dose dose) {
	doses.add(dose);
	dose.setFood(this);
	return this;
    }

    public Food removeDose(Dose dose) {
	doses.remove(dose);
	dose.setFood(null);
	return this;
    }
    
    public int getId() {
	return id;
    }
    public Food setId(int id) {
	this.id = id;
	return this;
    }
    public String getName() {
	return name;
    }
    public Food setName(String name) {
	this.name = name;
	return this;
    }
    // public Food getDefaultFood() {
    // 	return defaultFood;
    // }
    // public Food setDefaultFood(Food defaultFood) {
    // 	this.defaultFood = defaultFood;
    // 	return this;
    // }
    public Nutrients getNutrients() {
	return nutrients;
    }
    public Food setNutrients(Nutrients nutrients) {
	this.nutrients = nutrients;
	return this;
    }
    public Client getClient() {
	return client;
    }
    public Food setClient(Client client) {
	this.client = client;
	return this;
    }
    public List<Dose> getDoses() {
	return doses;
    }
    public Food setDoses(List<Dose> doses) {
	this.doses = doses;
	return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Food)) return false;
	return ((Food)obj).getId() == getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash( getClass().getName() );
    }
}
