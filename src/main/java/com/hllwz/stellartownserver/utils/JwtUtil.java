package com.hllwz.stellartownserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT工具类
 * @author Linorman
 * @version 1.0.0
 */
@Component
public class JwtUtil {
    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.expiration}")
    private long JWT_EXPIRATION;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_EXPIRATION;

//    /**
//     * 解析token字符串中的加密信息【加密算法&加密密钥】, 提取所有声明的方法
//     * @param token
//     * @return Claims
//     */
//    private Claims extractAllClaims(String token){
//        return Jwts
//                .parserBuilder()
//                // 获取alg开头的信息
//                .setSigningKey(getSignInKey())
//                .build()
//                // 解析token字符串
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * 获取签名密钥的方法
//     * @return Key
//     */
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    /**
//     * 解析token字符串中的权限信息
//     * @param token
//     * @return Claims
//     */
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    /**
//     * 从token中解析出username
//     * @param token
//     * @return String
//     */
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    /**
//     * 判断token是否过期
//     * @param token
//     * @param userDetails
//     * @return boolean
//     */
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        // 从token中获取用户名
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) &&!isTokenExpired(token);
//    }
//
//    /**
//     * 验证token是否过期
//     * @param token
//     * @return boolean
//     */
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//    /**
//     * 从授权信息中获取token过期时间
//     * @param token
//     * @return Date
//     */
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    /**
//     * 生成token
//     * @param userDetails
//     * @return String
//     */
//    public String generateToken(UserDetails userDetails) {
//        return createToken(userDetails.getUsername());
//    }
//
//    /**
//     * 生成token
//     * @param subject
//     * @return String
//     */
//    private String createToken(String subject) {
//        return Jwts
//                .builder()
//                // 设置token主题
//                .setSubject(subject)
//                // 设置token过期时间
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//                // 设置token签名
//                .signWith(getSignInKey())
//                .compact();
//    }

    /**
     * 从token中解析出username
     * @param token
     * @return String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 提取token中的声明
     * @param token
     * @return Claims
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 生成token
     * @param userDetails
     * @return String
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * 生成token
     * @param extraClaims
     * @param userDetails
     * @return String
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, JWT_EXPIRATION);
    }

    /**
     * 刷新token
     * @param userDetails
     * @return String
     */
    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, REFRESH_EXPIRATION);
    }

    /**
     * build token
     * @param extraClaims
     * @param userDetails
     * @param expiration
     * @return String
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return boolean
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * 验证token是否过期
     * @param token
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 从授权信息中获取token过期时间
     * @param token
     * @return Date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 提取token中的声明
     * @param token
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取签名密钥的方法
     * @return Key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

