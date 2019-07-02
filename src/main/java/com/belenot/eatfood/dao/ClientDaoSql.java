package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;


public class ClientDaoSql implements ClientDao {
    private Connection connection;
    public void setConnection(Connection connection) { this. connection = connection; }
    
    public Client getClientById(int id) throws Exception {
	Client client = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    client = new Client();
	    String login = rs.getString("login");
	    client.setId(id);
	    client.setLogin(login);
	}
	return client;
    }
    public Client getClientByLogin(String login, String password) throws Exception {
	Client client = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE login = ? AND password = ?");
	ps.setString(1, login);
	ps.setString(2, password);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    client = new Client();
	    int id = rs.getInt("id");
	    client.setId(id);
	    client.setLogin(login);
		
	}
	return client;
    }
    public Client addClient(String login, String password, String name, String surname, String email) throws Exception {
	PreparedStatement ps = connection.prepareStatement("INSERT INTO client (login, password) VALUES (?, ?)");
	ps.setString(1, login);
	ps.setString(2, password);
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM client ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	int id = -1;
	String addedLogin = null;
	if (rs.next()) {
	    id = rs.getInt("id");
	    addedLogin = rs.getString("login").trim();
	}
	    
	if (id < 0) throw new ApplicationException("Can't fetch added client with login = \"" + login + "\" to ClientDao(there's no such client)");
	if (!addedLogin.equals(login)) {
	    String msg = String.format("Can't fetch added client with login = \"" + login + "\" to ClientDao(last added client's login and parameter login are not equal(%s!=%s))", login, addedLogin);
	    throw new ApplicationException(msg);
	}
	Client client = new Client();
	client.setId(id);
	client.setLogin(login);
	return client;
    }
    public void updateClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.updateClient(Client client)) isn't supported yet");
    }
    public boolean deleteClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.deleteClient(Client client)) isn't supported yet");
    }
    
}
