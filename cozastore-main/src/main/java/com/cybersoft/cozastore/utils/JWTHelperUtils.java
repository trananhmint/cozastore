package com.cybersoft.cozastore.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTHelperUtils {
//@Value : Giúp lấy key khai báo bên file application.properties
    @Value("${jwt.token.key}")
    String secrectKey;
    /**
     *
     *  Bước 1 : Tạo Key
     *  Bước 2 : Sử dụng key mới tạo để sinh ra token
     *
     */

    public String generateToken(String data){
//       Lấy secrect key đã tạo trước đó sử dụng
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrectKey));
//        Dùng key để tạo ra token
        String token = Jwts.builder().setSubject(data).signWith(key).compact();
        return token;
    }

    public String validToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrectKey));
//        chuẩn bị chìa khóa để tiến hành giải mã
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token) //Truyền token cần giải mã
                .getBody().getSubject();
    }

}
