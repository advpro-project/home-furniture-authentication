package com.hoomgroom.authentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateTokenWithUserDetails() {
        UserDetails userDetails = User.builder()
                .username("ayamsigma@example.com")
                .password("password")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testGenerateTokenWithExtraClaimsAndUserDetails() {
        UserDetails userDetails = User.builder()
                .username("ayamsigma@example.com")
                .password("password")
                .roles("ADMIN")
                .build();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", "Ayam Sigma");

        String token = jwtService.generateToken(extraClaims, userDetails);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testIsTokenValid() {
        UserDetails userDetails = User.builder()
                .username("ayamsigma@example.com")
                .password("password")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(userDetails);

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testIsTokenInvalid() {
        UserDetails userDetails = User.builder()
                .username("ayamsigma@example.com")
                .password("password")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(userDetails);
        String finalToken = token + "invalid";

        assertThrows(io.jsonwebtoken.security.SignatureException.class, () -> jwtService.isTokenValid(finalToken, userDetails));
    }

    @Test
    void testExtractEmail() {
        UserDetails userDetails = User.builder()
                .username("ayamsigma@example.com")
                .password("password")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(userDetails);

        assertEquals("ayamsigma@example.com", jwtService.extractEmail(token));
    }
}
