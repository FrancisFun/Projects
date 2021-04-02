package com.aiot.web.util;

import com.aiot.web.user.entity.UserDTO;
import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */

/**
 * JWT工具类
 */
public class JwtUtil {
    /** 过期时间---24 hour */
    private static final int EXPIRATION_TIME = 60*60*24;
    /** 秘钥 */
    private static final String SECRET = "f26ME6kputw4c63L5Xgv6f7xMtlaagrgSYsS7Z7MqFHs6uS4CdstyU0ag63yZAxSv";
    /** Token前缀 */
    public static final String TOKEN_PREFIX = "Bearer ";
    /** 表头授权 */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 生成Token
     * @param userDTO 用户信息
     * @return Token
     */
    public static String generateToken(String username,String role,Long id) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // 设置签发时间
        calendar.setTime(new Date());
        // 设置过期时间
        // 添加秒钟
        calendar.add(Calendar.SECOND, EXPIRATION_TIME);
        Date time = calendar.getTime();
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("username", username);
        map.put("role", role);
        map.put("id", id);
        String jwt = Jwts.builder()
                .setClaims(map)
                //签发时间
                .setIssuedAt(now)
                //过期时间
                .setExpiration(time)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        //jwt前面一般都会加Bearer
        return TOKEN_PREFIX + jwt;
    }

    /**
     * 获取用户名
     * @param token Token
     * @return 用户名
     */
    public static String getUsername(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return body.get("username").toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取用户身份
     * @param token Token
     * @return 用户身份
     */
    public static String getRole(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return body.get("role").toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取用户ID
     * @param token Token
     * @return 用户ID
     */
    public static Long getId(String token){
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return Long.parseLong(body.get("id").toString());
        } catch (Exception e) {
            throw e;
        }
    }

    public static Boolean isSameUser(String token, Long id){
        return getId(token).equals(id);
    }

}
