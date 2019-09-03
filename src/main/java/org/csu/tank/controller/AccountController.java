package org.csu.tank.controller;


import org.csu.tank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public void login(String username,String password){
        accountService.getAccount(username,password);
    }
}
