package com.hank.springbootmall.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.hank.springbootmall.service.implement.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(CustomUserDetailsImpl userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("userId", userDetails.getUserId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .sign(algorithm);
    }

    public DecodedJWT extractClaims(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public Integer extractUserId(String token) {
        return extractClaims(token).getClaim("userId").asInt();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        final Integer userId = extractUserId(token);
        return (email.equals(userDetails.getUsername()) &&
                userId.equals(((CustomUserDetailsImpl) userDetails).getUserId()) &&
                !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiresAt().before(new Date());
    }
}