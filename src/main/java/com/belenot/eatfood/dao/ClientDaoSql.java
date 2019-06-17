package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
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

    public void init() throws SQLException {
	connection = DriverManager.getConnection(connectionAddress, username, password);
    }
    
    public Client getClient(long id) { return null; }
    public List<Client> getClientByLogin(String login) { return null; }
    public Client addClient(String login) { return null; }
    public Client updateClient(Client client) { return null; }
    public boolean deleteClient(Client client) { return false; }
    
}
