package org.csu.tank.util;

import com.alibaba.fastjson.JSONObject;
import org.csu.tank.domain.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.csu.tank.service.AccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author GH
 */
public class JwtUtil {

    public static final String JWT_ERRCODE_EXPIRE = "token已失效！";
    public static final String JWT_ERRCODE_FAIL = "token认证失败！";
    public static final String JWT_ERRCODE_UNKOWN = "未知错误！";
    public static final String JWT_SECERT = "eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3JlYXRlZCI6MTU0MDYxMzI4N";
    public static final Long EXPIRE_TIME = 36000000L;

    @Resource
    private AccountService accountService;

    public static String getToken(Account account){
        if (account.getUsername() == null){
            return null;
        }
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,JWT_SECERT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .setSubject(account.getUsername())
                .compact();
    }

    public static JSONObject parseObject(String token){
        return JSONObject.parseObject(JSONObject.toJSONString(Jwts.parser()
                .setSigningKey(JWT_SECERT)
                .parse(token).getBody()));
    }

    public static String getUsernameByToken(String token) {
        JSONObject object = parseObject(token);
        return (String) object.get("sub");
    }

    public static String getUsernameByRequest(HttpServletRequest request) {
        String token = request.getHeader("token");
        JSONObject object = parseObject(token);
        return (String) object.get("sub");
    }
}