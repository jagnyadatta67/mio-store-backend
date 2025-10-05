package com.miostore.auth;


import com.miostore.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // ⚙️ 1️⃣ Use a long, Base64-encoded secret key
    // (You can generate one with:  openssl rand -base64 64)
    private static final String SECRET_KEY =
            "bXlTdXBlclNlY3JldEtleU1pb09yZ2FuaWNzQXBwRm9yU2VjdXJlU2lnbmluZw==";

    // 🧩 2️⃣ Generate JWT Token
    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)                // store userId here
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hrs
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🧩 3️⃣ Validate Token
    public boolean isTokenValid(String token, User user) {
        final String userId = extractSubject(token);
        return (userId.equals(String.valueOf(user.getId())) && !isTokenExpired(token));
    }

    // 🧩 4️⃣ Extract user ID (subject)
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 🧩 5️⃣ Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 🧩 6️⃣ Check token expiry
    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // 🧩 7️⃣ Validate structure/signature only
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 🧩 8️⃣ Common helper for parsing
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🧩 9️⃣ Key builder
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
