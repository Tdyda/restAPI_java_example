package com.example.restapi_java.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateValidToken_withSubjectAndRoles() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_ADMIN", "ROLE_USER");

        String token = jwtService.generateToken(subject, roles);

        Claims claims = Jwts.parser()
                .verifyWith(jwtService.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        assertEquals(subject, claims.getSubject());
        assertEquals(roles, new HashSet<>((List<?>) claims.get("roles")));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    @Test
    void shouldExtractSubjectFromValidToken() {
        String subject = "john@example.com";
        String token = jwtService.generateToken(subject, Set.of("ROLE_USER"));

        String extracted = jwtService.extractSubject(token);

        assertEquals(subject, extracted);
    }

    @Test
    void shouldExtractRolesFromValidToken() {
        String subject = "john@example.com";
        String token = jwtService.generateToken(subject, Set.of("ROLE_USER"));
        Set<String> expectedRoles = Set.of("ROLE_USER");

        Set<String> actualRoles = jwtService.extractRoles(token);

        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void shouldReturnTrue_whenTokenIsValidAndNotExpired() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_USER", "ROLE_ADMIN");
        String token = jwtService.generateToken(subject, roles);

        Boolean isValid = jwtService.isTokenValid(token);

        assertEquals(true, isValid);
    }

    @Test
    void shouldReturnFalse_whenTokenIsExpired() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_USER", "ROLE_ADMIN");

        String expiredToken = Jwts.builder()
                .subject(subject)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000))
                .expiration(new Date(System.currentTimeMillis() - 60 * 60 * 1000))
                .signWith(jwtService.getKey())
                .compact();

        Boolean isValid = jwtService.isTokenValid(expiredToken);

        assertEquals(false, isValid);
    }

    @Test
    void shouldReturnFalse_whenTokenIsNotValid() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_USER", "ROLE_ADMIN");

        String invalidToken = Jwts.builder()
                .subject(subject)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(
                        "INVALID_SECRET_MIN_256_BITS_REQUIRED".getBytes()
                ))
                .compact();

        Boolean isValid = jwtService.isTokenValid(invalidToken);
        assertEquals(false, isValid);
    }
}
