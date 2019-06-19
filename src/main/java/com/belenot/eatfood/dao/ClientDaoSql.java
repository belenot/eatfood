package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.exception.ApplicationException;


public class ClientDaoSql implements ClientDao {
    private String connectionAddress;
    private String username;
    private String password;
    
    public void setConnectionAddress(String connectionAddress) { this.connectionAddress = connectionAddress; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    
    private Connection connection;

    /**
     * Assume, that corresponding driver loaded in lib directory 
     *
     */
    
    public void init() throws ApplicationException {
	try {
	    connection = DriverManager.getConnection(connectionAddress, username, password);
	} catch (SQLException exc) {
	    String msg = String.format("Can't connect to FoodDao(%s)", connectionAddress);
	    throw new ApplicationException(msg, exc);
	}
    }
    public void destroy() throws ApplicationException {
	try {
	    if (connection != null && !connection.isClosed())
		connection.close();
	} catch (SQLException exc) {
	    String msg = String.format("Can't close connection with FoodDao(%s)", connectionAddress);
	    throw new ApplicationException(msg, exc);
	}
    }
    public Client getClient(int id) throws ApplicationException {
	try {
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
	} catch (SQLException exc) {
	    String msg = String.format("Can't get client from ClientDao with id = %d", id);
	    throw new ApplicationException(msg, exc);
	}
    }
    public List<Client> getClientByLogin(String login, String password) throws ApplicationException {
	try { 
	    List<Client> clientList = new ArrayList<>();
	    PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE login = ? ");
	    ps.setString(1, login);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Client client = new Client();
		int id = rs.getInt("id");
		client.setId(id);
		client.setLogin(login);
		clientList.add(client);
	    }
	    return clientList;
	} catch (SQLException exc) {
	    String msg = String.format("Can't get client by login = \"%s\"", login);
	    throw new ApplicationException(msg, exc);
	}
    }
    public Client addClient(String login, String password) throws ApplicationException {
	try {
	    PreparedStatement ps = connection.prepareStatement("INSERT INTO client (login) VALUES (?)");
	    ps.setString(1, login);
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
	} catch (SQLException exc) {
	    String msg = String.format("Can't add client with login = \"%s\" to ClientDao", login);
	    throw new ApplicationException(msg, exc);
	}
    }
    public Client updateClient(Client client) throws ApplicationException {
	throw new ApplicationException("Method(ClientDao.updateClient(Client client)) isn't supported yet");
    }
    public boolean deleteClient(Client client) throws ApplicationException {
	throw new ApplicationException("Method(ClientDao.deleteClient(Client client)) isn't supported yet");
    }
    
}
