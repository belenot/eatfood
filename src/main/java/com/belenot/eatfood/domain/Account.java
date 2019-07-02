package com.belenot.eatfood.domain;

import java.util.Date;

public class Account {
    private String login;
    private String password;
    private Date regDate;
    private Client client;

    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
    public void setClient(Client client) { this.client = client; }
    
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public Date getRegDate() { return regDate; }
    public Client getClient() { return client; }

    @Override
    public String toString() {
	String str = String.format("Account:\n\tLogin: %s;\n\tRegDate: %s;\n\tClient: %d;\n",
				   login,
				   regDate != null ? regDate.toString() : null,
				   client != null ? client.getId() : null);
	return str;
    }
}
