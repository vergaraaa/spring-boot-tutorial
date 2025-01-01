package com.example.nobsv2.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    
    public static String generateToken(User user) {
        return Jwts
            .builder()
            .subject(user.getUsername())
            .expiration(new Date(System.currentTimeMillis() + 300_000)) // 5 min
            .signWith(getSigningKey())
            .compact();
    }

    
    public static Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token) {
        // can add more validation here
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("secretKeyLongEnoughToBeSecureAndNotGuessableByHacker");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
