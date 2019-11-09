package com.belenot.eatfood.service;

import com.belenot.eatfood.domain.User;
import com.belenot.eatfood.domain.UserProfile;
import com.belenot.eatfood.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username + " not found");
        return user;
    }

    public User registrate(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setProfile(new UserProfile());
        userRepository.save(user);
        return user;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User doesn't exists");
        }
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }

    public User currentUser() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}