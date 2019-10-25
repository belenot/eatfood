package com.belenot.eatfood.service;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.EatfoodException.EatfoodExceptionType;
import com.belenot.eatfood.repository.ClientRepository;
import com.belenot.eatfood.service.exception.ClientSignInException;
import com.belenot.eatfood.service.exception.ClientSignUpException;
import com.belenot.eatfood.user.ClientDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class ClientService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private  AuthenticationManager authenticationManager;

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails details = new ClientDetails(clientRepository.findByLogin(username));
        return details;
    }

    public Client signUp(Client client) {
        if (clientRepository.findByLogin(client.getLogin()) != null) {
            throw new ClientSignUpException("Such login has been already occuped", EatfoodExceptionType.CLIENT_ALREADY_EXISTS);
        }
        clientRepository.save(client);
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(client.getLogin(), client.getPassword());
        Authentication authenticationResult  = authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return client;
    }

    public Client signIn(String login, String password) throws ClientSignInException {
        String incorrectPasswordMessage = "Incorrect password for login " + login;
        String nullClientMessage = "Client with login " + login + " not found";
        Client client = clientRepository.findByLogin(login);
        if (client == null) {
            throw new ClientSignInException(nullClientMessage, EatfoodExceptionType.NO_SUCH_CLIENT);
        }
        if (!client.getPassword().equals(password)) {
            throw new ClientSignInException(incorrectPasswordMessage, EatfoodExceptionType.INCORRECT_PASSWORD);
        } else {
            Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(client.getLogin(), client.getPassword());
            Authentication authenticationResult  = authenticationManager.authenticate(authenticationRequest);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return client;
        }
    }

    public Client byId(Long id) {
        return clientRepository.findById(id).get();
    }

    public Client byLogin(String login) {
        return clientRepository.findByLogin(login);
    }

    
    
}