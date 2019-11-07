package com.belenot.eatfood.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private User author;
    private Food parent;
    @Embedded
    private Nutrients nutrients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Food getParent() {
        return parent;
    }

    public void setParent(Food parent) {
        this.parent = parent;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }
}