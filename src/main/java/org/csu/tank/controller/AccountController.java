package org.csu.tank.controller;


import org.csu.tank.domain.Account;
import org.csu.tank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Account login(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        Account account = accountService.getAccount(username, password);
        if (account != null)
            return account;
        else
            return null;
    }
}
