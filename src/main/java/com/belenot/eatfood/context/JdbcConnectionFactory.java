package com.belenot.eatfood.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

public class JdbcConnectionFactory {
    private String address;
    private String username;
    private String password;
    private Connection connection;
    public void setAddress(String address) { this.address = address; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public boolean isConnected() {
	try {
	    return connection != null && !connection.isClosed();
	} catch (Exception exc) {
	    return false;
	}
    }
    
    public Connection getConnection() {
	try {
	    if (connection == null) {
		connection = DriverManager.getConnection(address, username, password);
	    }
	    return connection;
	} catch (SQLException exc) {
	    String msg = String.format("Can't connection to database %s %s %s",address, username, password);
	    LogManager.getLogger().error(msg);
	    return null;
	}
    }
}
