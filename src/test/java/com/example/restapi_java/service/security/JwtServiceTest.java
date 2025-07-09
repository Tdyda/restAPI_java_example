package com.example.restapi_java.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "SECRET_KEY", "OMBhwobThAOSDnqKbksOKekHNyeHnrHWqJXcM5Bv/Rs=");
        jwtService.init();
    }

    @Test
    void shouldGenerateValidToken_withSubjectAndRoles() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_ADMIN", "ROLE_USER");

        String token = jwtService.generateToken(subject, roles);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(
                        "OMBhwobThAOSDnqKbksOKekHNyeHnrHWqJXcM5Bv/Rs=".getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

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
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000))
                .setExpiration(new Date(System.currentTimeMillis() - 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(
                        "OMBhwobThAOSDnqKbksOKekHNyeHnrHWqJXcM5Bv/Rs=".getBytes()
                ))
                .compact();

        Boolean isValid = jwtService.isTokenValid(expiredToken);

        assertEquals(false, isValid);
    }

    @Test
    void shouldReturnFalse_whenTokenIsNotValid() {
        String subject = "john@example.com";
        Set<String> roles = Set.of("ROLE_USER", "ROLE_ADMIN");

        String invalidToken = Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(
                        "INVALID_SECRET_MIN_256_BITS_REQUIRED".getBytes()
                ))
                .compact();

        Boolean isValid = jwtService.isTokenValid(invalidToken);
        assertEquals(false, isValid);
    }
}
