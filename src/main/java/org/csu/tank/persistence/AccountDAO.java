package org.csu.tank.persistence;

import org.csu.tank.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertAddress(Account account);

    void updateAccount(Account account);  //修改账户信息

    void updateProfile(Account account);  //修改账户信息


}
