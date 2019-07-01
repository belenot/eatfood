package com.belenot.eatfood.dao;

import com.belenot.eatfood.domain.Account;
import com.belenot.eatfood.domain.Client;

public interface AccountDao {
    Account newAccount(Account account) throws Exception;
    Account getAccountByClient(Client client) throws Exception;
    Account getAccount(Account account) throws Exception;
}
