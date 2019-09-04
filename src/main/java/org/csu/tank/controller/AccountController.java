package org.csu.tank.controller;


import org.csu.tank.domain.Account;
import org.csu.tank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;

        @PostMapping("/login")
        public Account login(@RequestBody Account account) {
            String username = account.getUsername();
            String password = account.getPassword();
            Account account1 = accountService.getAccount(username,password);
            if (account1 != null)
                return account1;
            else
                return null;
        }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @RequestBody
//    public JwtUtil login(@ResponseBody Map<String,String> map){
//        return JwtUtil
//    }

    @GetMapping("/account/{username}")
    public Account getAccountByUsername(@PathVariable("username")String username){
        return accountService.getAccountByUsername(username);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Account account){
            try {
                accountService.insertAccount(account);
                return true;
            }
            catch (Exception e){
                return false;
            }
    }
}
