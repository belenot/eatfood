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
	    client.setId(rs.getInt("id"));
	    client.setLogin(rs.getString("login"));
	    client.setPassword(rs.getString("password"));
	    client.setName(rs.getString("name"));
	    client.setSurname(rs.getString("surname"));
	    client.setEmail(rs.getString("email"));
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
	    client.setId(rs.getInt("id"));
	    client.setLogin(rs.getString("login"));
	    client.setPassword(rs.getString("password"));
	    client.setName(rs.getString("name"));
	    client.setSurname(rs.getString("surname"));
	    client.setEmail(rs.getString("email"));
	}
	return client;
    }
    public Client addClient(String login, String password, String name, String surname, String email) throws Exception {
	Client client = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO client (login, password, name, surname, email) VALUES (?, ?, ?, ?, ?)");
	ps.setString(1, login);
	ps.setString(2, password);
	ps.setString(3, name);
	ps.setString(4, surname);
	ps.setString(5, email);
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM client ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	int id = -1;
	String addedLogin = null;
	if (rs.next()) {
	    client = new Client();
	    client.setId(rs.getInt("id"));
	    client.setLogin(rs.getString("login"));
	    client.setPassword(rs.getString("password"));
	    client.setName(rs.getString("name"));
	    client.setSurname(rs.getString("surname"));
	    client.setEmail(rs.getString("email"));
	} else {
	    String msg = String.format("Can't fetch added client with login = \"" + login + "\" to ClientDao(last added client's login and parameter login are not equal(%s!=%s))", login, addedLogin);
	    throw new ApplicationException(msg);
	}
	return client;
    }
    public void updateClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.updateClient(Client client)) isn't supported yet");
    }
    public boolean deleteClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.deleteClient(Client client)) isn't supported yet");
    }
    
}
