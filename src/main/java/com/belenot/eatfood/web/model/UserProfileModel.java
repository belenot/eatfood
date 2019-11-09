package com.belenot.eatfood.web.model;

import java.util.List;
import java.util.stream.Collectors;

import com.belenot.eatfood.domain.User;

public class UserProfileModel {
    private Long id;
    private String username;
    private List<Long> friendsIds;
    private String visibility;
    
    public UserProfileModel(User user) {
        id = user.getId();
        username = user.getUsername();
        friendsIds = user.getProfile().getFriends().stream().map(friend->friend.getId()).collect(Collectors.toList());
        visibility = user.getProfile().getVisibility().toString();
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public List<Long> getFriendsIds() {
        return friendsIds;
    }
    public String getVisibility() {
        return visibility;
    }
}