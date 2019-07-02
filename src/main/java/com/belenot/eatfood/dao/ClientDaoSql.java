package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.belenot.eatfood.domain.Account;
import com.belenot.eatfood.domain.Client;


public class ClientDaoSql implements ClientDao {
    private Connection connection;
    private AccountDaoSql accountDaoSql;
    public void setConnection(Connection connection) { this.connection = connection; }
    public void setAccountDaoSql(AccountDaoSql accountDaoSql) { this.accountDaoSql = accountDaoSql; }
    @Override
    public Client getClientById(int id) throws Exception {
	Client client = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    client = new Client();
	    client.setId(id);
	    client.setName(rs.getString("name"));
	    client.setEmail(rs.getString("email"));
	    client.setSurname(rs.getString("surname"));
	}
	return client;
    }
    @Override
    public Client getClientByAccount(Account account) throws Exception {
	Client client = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM client JOIN account ON client.id = account.client WHERE account.login = ? AND account.password = ?");
	ps.setString(1, account.getLogin());
	ps.setString(2, account.getPassword());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    client = new Client();
	    client.setId(rs.getInt("client.id"));
	    client.setName(rs.getString("client.name"));
	    client.setSurname(rs.getString("client.surname"));
	    client.setEmail(rs.getString("client.email"));
	}
	return client;
    }
    @Override
    public Client newClient(Client client, Account account) throws Exception {
	Client clientResult = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO client (name, surname, email) VALUES (?, ?, ?)");
	ps.setString(1, client.getName());
	ps.setString(2, client.getSurname());
	ps.setString(3, client.getEmail());
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM client ORDER BY id DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    clientResult = new Client();
	    clientResult.setId(rs.getInt("id"));
	    clientResult.setName(rs.getString("name"));
	    clientResult.setSurname(rs.getString("surname"));
	    clientResult.setEmail(rs.getString("email"));
	}
	account.setClient(clientResult);
	accountDaoSql.newAccount(account);
	return clientResult;
    }
    @Override
    public Client updateClient(Client client) throws Exception {
	Client clientResult = null;
	PreparedStatement ps = connection.prepareStatement("UPDATE client SET name = ?, surname = ?, email = ? WHERE id = ?");
	ps.setString(1, client.getName());
	ps.setString(2, client.getSurname());
	ps.setString(3, client.getEmail());
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
	ps.setInt(1, client.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    clientResult = new Client();
	    clientResult.setId(rs.getInt("id"));
	    clientResult.setName(rs.getString("name"));
	    clientResult.setSurname(rs.getString("surname"));
	    clientResult.setEmail(rs.getString("email"));
	}
	return clientResult;
    }
    @Override
    public Client deleteClient(Client client) throws Exception {
	Client clientResult = null;
	PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE id = ?");
	ps.setInt(1, client.getId());
	ps.execute();
	clientResult = new Client();
	clientResult.setName(client.getName());
	clientResult.setSurname(client.getSurname());
	clientResult.setEmail(client.getEmail());
	return clientResult;
    }

    @Override
    public String toString() {
	String str = String.format("ClientDaoSql{%s, AccountDaoSql=%s}",
				   connection != null ? connection.toString() : null, accountDaoSql);
	return str;
    }
	
}
