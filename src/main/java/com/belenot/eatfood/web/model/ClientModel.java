package com.belenot.eatfood.web.model;

import com.belenot.eatfood.domain.Client;

public class ClientModel {
    private Long id;
    private String login;
    private String name;

    public ClientModel() {}

    private ClientModel(Client client) {
        this.id = client.getId();
        this.login = client.getLogin();
        this.name = client.getName();
    }

    public static ClientModel of(Client client) {
        return new ClientModel(client);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}