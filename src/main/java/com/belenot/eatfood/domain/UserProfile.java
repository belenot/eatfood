package com.belenot.eatfood.domain;

import java.util.List;

import com.belenot.eatfood.domain.User;

public class UserProfile {

    public enum Visibility { ALL, FRIENDS, NONE }
    private List<User> friends;
    private Visibility visibility;

}