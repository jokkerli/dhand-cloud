package com.jokeer.dhand.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jokeer.dhand.bean.User;

public class JWTUtils {

    /**
     * 创建Token
     * @param user
     * @return
     */
    public static String createJWT(User user){
        String token = "";
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
