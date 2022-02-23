package com.zjp.generatemysql.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {

    // Token过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;
    // 用户名称
    public static final String USER_NAME = "username";

    /* *
     * @Author lsc
     * <p> 校验token是否正确 </p>
     * @Param token
     * @Param username
     * @Param secret
     * @Return boolean
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim(USER_NAME, username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /* *
     * @Author lsc
     * <p>生成签名,30min后过期 </p>
     * @Param [username, secret]
     * @Return java.lang.String
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim(USER_NAME, username).withExpiresAt(date).sign(algorithm);
    }

    /* *
     * @Author lsc
     * <p> 获得用户名 </p>
     * @Param [request]
     * @Return java.lang.String
     */
    public static String getUserNameByToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(USER_NAME).asString();
    }

    // 是否已过期
    public static boolean isExpiration(String token, String Signing) {
        try {
            return getTokenBody(token, Signing).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration().before(new Date());
        }
    }

    private static Claims getTokenBody(String token, String Signing) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Signing)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("JWT格式验证失败:" + token);
        }
        return claims;
    }


    /**
     * token时间没有超过期时间的两倍，续期，否则重新登录
     *
     * @param token
     * @return
     */
    public static boolean isTwoTimesTokenExpiration(String token, String Signing) {
        try {
            Claims tokenBody = getTokenBody(token, Signing);
            Date expiration = tokenBody.getExpiration();
            return expiration.getTime() + EXPIRE_TIME * 2 < System.currentTimeMillis();
        } catch (ExpiredJwtException e) {
            long expirationTime = e.getClaims().getExpiration().getTime() + EXPIRE_TIME * 1000 * 2;
            return expirationTime < System.currentTimeMillis();
        }
    }


    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token, String Signing) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Signing)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("JWT格式验证失败:" + token);
        }
        return claims;
    }


    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token, String Signing) {
        Claims claims = getClaimsFromToken(token, Signing);
        return claims.getExpiration();
    }

    public static void main(String[] args) {
//        Date date = getExpiredDateFromToken(
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU0Mjg0NjYsInVzZXJuYW1lIjoicm9vdCJ9.CLH1dB7gZ_3vQzootAz_r_eTZTBwE8C-dY4l-3v76Ro",
//                "9c102f25233787c60a07e5a6cba789a3");
        boolean b = isTwoTimesTokenExpiration(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU0Mjg0NjYsInVzZXJuYW1lIjoicm9vdCJ9.CLH1dB7gZ_3vQzootAz_r_eTZTBwE8C-dY4l-3v76Ro",
                "9c102f25233787c60a07e5a6cba789a3");
        System.out.println(b);
    }

}
