package com.cx330.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cx330.constant.ConstantValue;
import com.cx330.entity.User;

import java.util.Date;
import java.util.Map;

public class JWTUtil {
    // 生成令牌
    public static String setToken(User user){
        return JWT.create()
                .withClaim("user", user.toJson())   //添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + ConstantValue.JWT_EXPIRE_TIME))  //设置有效时间为5秒
                .sign(Algorithm.HMAC256(ConstantValue.JWT_SECRET));   //设置签名
    }

    // 解析令牌
    public static User parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ConstantValue.JWT_SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return User.fromJson(decodedJWT.getClaim("user").asString());
    }
    // 验证令牌
    public static boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ConstantValue.JWT_SECRET)).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
