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
    public Client addClient(Client client) throws Exception {
	Client clientReturn = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO client (login, password, name, surname, email) VALUES (?, ?, ?, ?, ?)");
	ps.setString(1, client.getLogin());
	ps.setString(2, client.getPassword());
	ps.setString(3, client.getName());
	ps.setString(4, client.getSurname());
	ps.setString(5, client.getEmail());
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM client ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    clientReturn = new Client();
	    clientReturn.setId(rs.getInt("id"));
	    clientReturn.setLogin(rs.getString("login"));
	    clientReturn.setPassword(rs.getString("password"));
	    clientReturn.setName(rs.getString("name"));
	    clientReturn.setSurname(rs.getString("surname"));
	    clientReturn.setEmail(rs.getString("email"));
	    return clientReturn;
	} else {
	    String msg = String.format("Can't fetch added client with login = \"" + client.getLogin() + "\" to ClientDao(last added client's login and parameter login are not equal(%s!=%s))", client.getLogin(), clientReturn);
	    throw new ApplicationException(msg);
	}
    }
    public void updateClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.updateClient(Client client)) isn't supported yet");
    }
    public boolean deleteClient(Client client) throws Exception {
	throw new Exception("Method(ClientDao.deleteClient(Client client)) isn't supported yet");
    }
    
}
