package org.csu.tank.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //过期时间15分钟
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    //token私钥

    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    /*
     * 生成签名 15min过期
     *@param username用户名
     * @return 加密的token
     * */
    public static String sign(String username,String userId){
        try{
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","hs256");
            //附带username ,userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName",username)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return null;
        }
    }

    /*
    * 校验token是否正确
    *
    * @param token秘钥
    * @return 是否正确
    * */
    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
