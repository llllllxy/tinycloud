package org.liuxingyu.tinycloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;


/**
 * jwt工具类
 *
 * @author liuxingyu01
 * @since 2021-4-25 16:29:08
 **/
public class JwtUtils {

    // jwt签名密钥
    private static final String jwtSecret = "828jsjkwksk2ii2kskskss";


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createJwt(Map<String, Object> claims) {
        Date createTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.HOUR, 24);

        String token = Jwts.builder()
                // 设置携带的内容即数据信息
                .setClaims(claims)
                // 设置过期时间
                .setExpiration(calendar.getTime())
                // 设置创建时间
                .setIssuedAt(createTime)
                // 设置签名算法和签名密钥
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }



    public static void main(String[] args) {

        // 创建token
        Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", UUID.randomUUID().toString().replace("-", ""));
        String token = createJwt(claims);

        System.out.println("token = " + token);

        // 解析token
        Claims parseClaims = parseJwt(token);

        System.out.println("parseClaims = " + parseClaims);
    }
}
