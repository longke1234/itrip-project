package com.lk.shiro;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : jwt工具类，拥护生成和校验jwt
 * @date : 2020-11-18 10:07
 */
public class JwtUtil {

    /**过期时间为2小时*/
    private static final long EXPIRE_TIME = 2*60*60*1000;

    /**
     * 校验用户名和密码
     * @param token
     * @param username 用户名
     * @param secret 密码
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //密码加密
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 获取token中的信息无需secret解密也能获得
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            //解密token
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").toString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    /**
     * 生成JWTToken
     * @param username jwt载荷的信息
     * @param secret 指定的加密秘钥
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sign(String username, String secret) throws
            UnsupportedEncodingException {
        //过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //指定加密规则和秘钥
        Algorithm algorithm = Algorithm.HMAC512(secret);
        // 附带自定载荷信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}