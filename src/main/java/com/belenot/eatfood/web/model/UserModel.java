package com.belenot.eatfood.web.model;

import com.belenot.eatfood.domain.User;

public class UserModel {
    private Long id;
    private String username;

    public UserModel(User user) {
        id = user.getId();
        username = user.getUsername();
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
}