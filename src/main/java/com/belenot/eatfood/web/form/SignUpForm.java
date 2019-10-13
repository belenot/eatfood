package com.belenot.eatfood.web.form;

import com.belenot.eatfood.domain.Client;

public class SignUpForm {
    private String login;
    private String password;
    private String name;

    public Client createDomain() {
        Client client = new Client();
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        return client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}