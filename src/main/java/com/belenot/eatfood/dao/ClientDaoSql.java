package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.belenot.eatfood.domain.Client;


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
    
    public void init() throws SQLException {
	connection = DriverManager.getConnection(connectionAddress, username, password);
    }

    public void destroy() throws SQLException {
	if (connection != null && !connection.isClosed())
	    connection.close();
    }
    
    public Client getClient(int id) {
	Client client = null;
	try {
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
	    /*log*/ 
	} 
	return client;
    }


    public List<Client> getClientByLogin(String login) { return null; }
    public Client addClient(String login) { return null; }
    public Client updateClient(Client client) { return null; }
    public boolean deleteClient(Client client) { return false; }
    
}
