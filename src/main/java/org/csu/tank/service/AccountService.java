package org.csu.tank.service;

import org.csu.tank.domain.Account;

public interface AccountService {
    Account getAccountByUsername(String username);
    Account getAccount(String username, String password);
    void insertAccount(Account account);

//    void updateAccount(Account account);  //修改账户信息
}
