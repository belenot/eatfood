package com.belenot.eatfood.domain;

import java.util.List;

import javax.persistence.ManyToMany;

//@Embeddable Should I use this, if I marked field with @Embedded?
public class UserProfile {

    public enum Visibility { ALL, FRIENDS, NONE }
    @ManyToMany//Uni- or bidirectional?(Forwhile unidirectional)
    private List<User> friends;
    private Visibility visibility;

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

}