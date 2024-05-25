package com.hoomgroom.authentication.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequestBuilder()
                .email("ayamsigma@gmail.com")
                .password("ayamsigma@gmail.com")
                .build();
    }

    @Test
    void testBuilder() {
        assertNotNull(loginRequest);
        assertEquals("ayamsigma@gmail.com", loginRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", loginRequest.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        loginRequest.setEmail("sapisigma@gmail.com");
        assertEquals("sapisigma@gmail.com", loginRequest.getEmail());

        loginRequest.setPassword("newpassword");
        assertEquals("newpassword", loginRequest.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        LoginRequest fullLoginRequest = new LoginRequest("ayamsigma@gmail.com", "ayamsigma@gmail.com");

        assertEquals("ayamsigma@gmail.com", fullLoginRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", fullLoginRequest.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        LoginRequest noArgsLoginRequest = new LoginRequest();

        assertNull(noArgsLoginRequest.getEmail());
        assertNull(noArgsLoginRequest.getPassword());

        noArgsLoginRequest.setEmail("newemail@gmail.com");
        noArgsLoginRequest.setPassword("newpassword");

        assertEquals("newemail@gmail.com", noArgsLoginRequest.getEmail());
        assertEquals("newpassword", noArgsLoginRequest.getPassword());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(loginRequest, loginRequest);
    }

    @Test
    void testEqualsDifferentObjectsWithDifferentValues() {
        LoginRequest anotherLoginRequest = new LoginRequestBuilder()
                .email("sapisigma@gmail.com")
                .password("sapisigma@gmail.com")
                .build();

        assertNotEquals(loginRequest, anotherLoginRequest);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, loginRequest);
    }

    @Test
    void testEqualsWithDifferentType() {
        assertNotEquals("Not a LoginRequest", loginRequest);
    }

    @Test
    void testHashCodeDifferentObjects() {
        LoginRequest anotherLoginRequest = new LoginRequestBuilder()
                .email("sapisigma@gmail.com")
                .password("sapisigma@gmail.com")
                .build();

        assertNotEquals(loginRequest.hashCode(), anotherLoginRequest.hashCode());
    }
}
