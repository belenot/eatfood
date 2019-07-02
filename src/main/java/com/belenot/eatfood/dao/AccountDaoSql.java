package com.belenot.eatfood.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.belenot.eatfood.domain.Account;
import com.belenot.eatfood.domain.Client;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountDaoSql implements AccountDao {
    private Connection connection;
    @Autowired
    private ClientDaoSql clientDaoSql;
    public void setConnection(Connection connection) { this.connection = connection; }
    public void setClientDaoSql(ClientDaoSql clientDaoSql) { this.clientDaoSql = clientDaoSql; }

    @Override
    public Account newAccount(Account account) throws Exception {
	Account accountResult = null;
	PreparedStatement ps = connection.prepareStatement("INSERT INTO account (login, password, client, reg_date) VALUES (?, ?, ?, ?)");
	ps.setString(1, account.getLogin());
	ps.setString(2, account.getPassword());
	ps.setInt(3, account.getClient().getId());
	ps.setDate(4, new Date(System.currentTimeMillis()));
	ps.execute();
	ps = connection.prepareStatement("SELECT * FROM account ORDER BY client DESC LIMIT 1");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    accountResult = new Account();
	    accountResult.setLogin(rs.getString("login"));
	    accountResult.setPassword(rs.getString("password"));
	    accountResult.setClient(clientDaoSql.getClientById(rs.getInt("client")));
	    accountResult.setRegDate(rs.getDate("reg_date"));
	}
	return accountResult;
    }

    @Override
    public Account getAccountByClient(Client client) throws Exception {
	Account accountResult = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM account WHERE client = ?");
	ps.setInt(1, client.getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    accountResult = new Account();
	    accountResult.setLogin(rs.getString("login"));
	    accountResult.setPassword(rs.getString("password"));
	    accountResult.setRegDate(rs.getDate("reg_date"));
	    accountResult.setClient(clientDaoSql.getClientById(rs.getInt("client")));
	}
	return accountResult;
    }

    @Override
    public Account getAccount(Account account) throws Exception {
	Account accountResult = null;
	PreparedStatement ps = connection.prepareStatement("SELECT * FROM account WHERE client = ?");
	ps.setInt(1, account.getClient().getId());
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    accountResult = new Account();
	    accountResult.setLogin(rs.getString("login"));
	    accountResult.setPassword(rs.getString("password"));
	    accountResult.setRegDate(rs.getDate("reg_date"));
	    accountResult.setClient(clientDaoSql.getClientById(rs.getInt("client")));
	}
	return accountResult;
    }

    @Override
    public String toString() {
	String str = String.format("AccountDaoSql: %s", connection != null ? connection.toString() : null);
	return str;
    }

}
    
