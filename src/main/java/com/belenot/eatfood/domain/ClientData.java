package com.belenot.eatfood.domain;

public class ClientData {
    private String name;
    private String surname;
    private String email;

    public ClientData() { }
    public ClientData(String name, String surname, String email) {
	this.name = name;
	this.surname = surname;
	this.email = email;
    }
    
    public String getName() {
	return name;
    }
    public ClientData setName(String name) {
	this.name = name;
	return this;
    }
    public String getSurname() {
	return surname;
    }
    public ClientData setSurname(String surname) {
	this.surname = surname;
	return this;
    }
    public String getEmail() {
	return email;
    }
    public ClientData setEmail(String email) {
	this.email = email;
	return this;
    }
}
