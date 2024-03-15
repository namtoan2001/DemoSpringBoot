package com.example.demorestapi.interfaces;

import com.example.demorestapi.viewModels.GenerateJwtRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

public interface IJwtService {
    String generateJwt(GenerateJwtRequest request);

    Claims decodeJwt(String token) throws JwtException;
}
