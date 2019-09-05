package org.csu.tank.controller;

import com.alibaba.fastjson.JSONObject;
import org.csu.tank.base.Response;
import org.csu.tank.util.JwtUtil;
import org.csu.tank.domain.Account;
import org.csu.tank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.csu.tank.base.Response.fail;
import static org.csu.tank.base.Response.success;

@RestController
@CrossOrigin(allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Response login(@RequestBody Account account, HttpServletRequest request) {
        String username = account.getUsername();
        String password = account.getPassword();
        Account account1 = accountService.getAccount(username, password);
        if (account1 != null) {
            JSONObject object = new JSONObject();
            object.put("tokenId", JwtUtil.getToken(account1));
            object.put("account", account1);

            return success(object);

        } else
            return fail("用户名或密码错误，请重试！");
    }


    @GetMapping("/account")
    public Response getAccountByUsername(HttpServletRequest request) {
        String tokenName = JwtUtil.getUsernameByToken(request.getHeader("token"));
        JSONObject object = new JSONObject();
        Account account = accountService.getAccountByUsername(tokenName);
        object.put("account", account);
        return success(object);
    }

    @PostMapping("/register")
    public Response register(@RequestBody Account account) {
        try {
            accountService.insertAccount(account);
            JSONObject object = new JSONObject();
            object.put("flag", true);
            return success(object);
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("flag", false);
            return fail("注册失败！");
        }
    }
}
