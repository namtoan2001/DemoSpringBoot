package com.example.demorestapi.services;

import com.example.demorestapi.interfaces.IJwtService;
import com.example.demorestapi.viewModels.GenerateJwtRequest;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService implements IJwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;
    private Key jwtSecretKey;

    @PostConstruct
    public void init() {
        jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Override
    public String generateJwt(GenerateJwtRequest request) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000);
        return Jwts.builder()
                .setSubject(String.valueOf(request.getId()))
                .claim("userName", request.getUserName())
                .setIssuedAt(now)
                .setIssuer(jwtIssuer)
                .setExpiration(expiration)
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public Claims decodeJwt(String token) throws JwtException {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return jws.getBody();
        } catch (JwtException e) {
            System.err.println("JWT Decoding Error: " + e.getMessage());
            throw e;
        }
    }
}

