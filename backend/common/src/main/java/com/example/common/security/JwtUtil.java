package com.example.common.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    // 建议从配置文件中读取
    private static final String SECRET = "your-secret-key"; // 替换为自己的密钥
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1小时（毫秒）

    /**
     * 生成 JWT Token
     * @param userid 用户名或唯一标识
     * @return token 字符串
     */
    public static String generateToken(String userid) {
        return JWT.create()
                .withSubject(userid)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验 token 是否有效（包括签名和过期时间）
     * @param token 待验证的 token
     * @return 校验通过返回 true，否则返回 false
     */
    public static boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 从 token 中提取用户名
     * @param token token 字符串
     * @return userId
     */
    public static String getUserIdByToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
        return decodedJWT.getSubject();
    }
}
