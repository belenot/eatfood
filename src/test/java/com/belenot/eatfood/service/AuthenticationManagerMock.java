package com.belenot.eatfood.service;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.repository.ClientRepositoryMock;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationManagerMock implements AuthenticationManager {

    private ClientService clientService;

    public AuthenticationManagerMock(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordToken = (UsernamePasswordAuthenticationToken) authentication;
        String login = usernamePasswordToken.getName();
        String password = usernamePasswordToken.getCredentials().toString();
        UserDetails details = clientService.loadUserByUsername(login);
        if (details == null) {
            throw new AuthenticationCredentialsNotFoundException("(mock) No such client");
        }
        if (details.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());
        } else {
            throw new AuthenticationServiceException("(mock) Wrong password");
        }
    }

}