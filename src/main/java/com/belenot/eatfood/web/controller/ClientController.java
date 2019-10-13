package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.service.ClientService;
import com.belenot.eatfood.user.ClientDetails;
import com.belenot.eatfood.web.form.SignInForm;
import com.belenot.eatfood.web.form.SignUpForm;
import com.belenot.eatfood.web.model.ClientModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/me")
    @ResponseBody
    public ClientModel getCient() {
        Client client = ((ClientDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClient();
        return ClientModel.of(client);
    }

    @PostMapping(path = "/signup")
    @ResponseBody
    public ClientModel signUp(@RequestBody SignUpForm form) {
        Client client = form.createDomain();
        client = clientService.signUp(client);
        return ClientModel.of(client);
    }

    @PostMapping(path = "/signin")
    @ResponseBody
    public ClientModel signIn(@RequestBody SignInForm form) {
        Client client = clientService.signIn(form.getLogin(), form.getPassword());
        return ClientModel.of(client);
    }

}