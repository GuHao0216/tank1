package org.csu.tank.persistence;

import org.apache.ibatis.annotations.Param;
import org.csu.tank.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    Account getAddress(@Param("username") String username,@Param("addressId") int addressId);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertAddress(Account account);

    void updateAccount(Account account);  //修改账户信息

    void updateProfile(Account account);  //修改账户信息


}
