package com.ecpss.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.util.Map;

public class JwtUtil {
    /**
     *
     * @param key 浏览器 key
     * @param param 用户信息
     * @param salt 服务器的 信息ip+time
     * @return
     */
    public static String encode(String key,Map<String,Object> param,String salt){
        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,key);
        
        jwtBuilder = jwtBuilder.setClaims(param);
        
        String token = jwtBuilder.compact();
        return token;
        
    }
    
    /**
     * 点击登录按钮，通过jwt 生成token 返回网站，在拦截器里将返回的token 写入cookie
     * @param args
     */
    public static void main(String[] args) {
    
    }
    
    public  static Map<String,Object> decode(String token , String key, String salt){
        Claims claims=null;
        if (salt!=null){
            key+=salt;
        }
        try {
            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch ( JwtException e) {
            return null;
        }
        return  claims;
    }
    
}
