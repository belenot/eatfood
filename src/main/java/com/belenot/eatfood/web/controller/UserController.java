package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.domain.User;
import com.belenot.eatfood.service.UserService;
import com.belenot.eatfood.web.form.UserAuthenticationForm;
import com.belenot.eatfood.web.form.UserRegistrationForm;
import com.belenot.eatfood.web.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/registration")
    public UserModel registration(@RequestBody UserRegistrationForm form) {
        User user = userService.registrate(form.getUsername(), form.getPassword());
        userService.authenticate(form.getUsername(), form.getPassword());
        return new UserModel(user);
    }

    @PostMapping("/authentication")
    public UserModel authentication(@RequestBody UserAuthenticationForm form) {
        User user = userService.authenticate(form.getUsername(), form.getPassword());
        return new UserModel(user);
    }

    @GetMapping("/me")
    public UserModel me() {
        return new UserModel(userService.currentUser());
    }

}