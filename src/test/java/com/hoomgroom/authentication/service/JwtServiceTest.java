package com.hoomgroom.authentication.service;

import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "ABBC0919662E8A70501B1CA15C511EFAD2AE40B9B37AE44811107FDA3123095B");
        jwtService.init();
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

        assertThrows(SignatureException.class, () -> jwtService.isTokenValid(finalToken, userDetails));
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