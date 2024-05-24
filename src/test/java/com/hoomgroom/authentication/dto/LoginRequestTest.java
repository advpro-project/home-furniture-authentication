package com.hoomgroom.authentication.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = LoginRequest.builder()
                .email("ayamsigma@gmail.com")
                .password("ayamsigma@gmail.com")
                .build();
    }

    @Test
    public void testBuilder() {
        assertNotNull(loginRequest);
        assertEquals("ayamsigma@gmail.com", loginRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", loginRequest.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        loginRequest.setEmail("sapisigma@gmail.com");
        assertEquals("sapisigma@gmail.com", loginRequest.getEmail());

        loginRequest.setPassword("newpassword");
        assertEquals("newpassword", loginRequest.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        LoginRequest fullLoginRequest = new LoginRequest("ayamsigma@gmail.com", "ayamsigma@gmail.com");

        assertEquals("ayamsigma@gmail.com", fullLoginRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", fullLoginRequest.getPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        LoginRequest noArgsLoginRequest = new LoginRequest();

        assertNull(noArgsLoginRequest.getEmail());
        assertNull(noArgsLoginRequest.getPassword());

        noArgsLoginRequest.setEmail("newemail@gmail.com");
        noArgsLoginRequest.setPassword("newpassword");

        assertEquals("newemail@gmail.com", noArgsLoginRequest.getEmail());
        assertEquals("newpassword", noArgsLoginRequest.getPassword());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(loginRequest, loginRequest);
    }

    @Test
    public void testEqualsDifferentObjectsWithDifferentValues() {
        LoginRequest anotherLoginRequest = LoginRequest.builder()
                .email("sapisigma@gmail.com")
                .password("sapisigma@gmail.com")
                .build();

        assertNotEquals(loginRequest, anotherLoginRequest);
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(loginRequest.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        assertFalse(loginRequest.equals("Not a LoginRequest"));
    }

    @Test
    public void testHashCodeDifferentObjects() {
        LoginRequest anotherLoginRequest = LoginRequest.builder()
                .email("sapisigma@gmail.com")
                .password("sapisigma@gmail.com")
                .build();

        assertNotEquals(loginRequest.hashCode(), anotherLoginRequest.hashCode());
    }
}
