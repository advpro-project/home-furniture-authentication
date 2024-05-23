package com.hoomgroom.authentication.dto;

import com.hoomgroom.authentication.model.User;
import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {

    private LoginResponse loginResponse;

    @BeforeEach
    void setUp() {
        loginResponse = LoginResponse.builder()
                .token("exampleToken")
                .build();
    }

    @Test
    public void testBuilder() {
        assertNotNull(loginResponse);
        assertEquals("exampleToken", loginResponse.getToken());
    }

    @Test
    public void testSettersAndGetters() {
        loginResponse.setToken("newToken");
        assertEquals("newToken", loginResponse.getToken());
    }

    @Test
    public void testAllArgsConstructor() {
        User user = new User(
                "Ayam Sigma",
                LocalDate.of(1990, 5, 15),
                Gender.MALE,
                "ayamsigma",
                "ayamsigma@example.com",
                "password",
                Role.ADMIN,
                100.0,
                null
        );

        LoginResponse fullLoginResponse = new LoginResponse("exampleToken", user);

        assertEquals("exampleToken", fullLoginResponse.getToken());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(loginResponse, loginResponse);
    }

    @Test
    public void testEqualsDifferentObjectsWithSameValues() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("exampleToken")
                .build();

        assertEquals(loginResponse, anotherLoginResponse);
    }

    @Test
    public void testEqualsDifferentObjectsWithDifferentValues() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse, anotherLoginResponse);
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(loginResponse.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        assertFalse(loginResponse.equals("Not a LoginResponse"));
    }

    @Test
    public void testHashCodeSameObjects() {
        LoginResponse sameLoginResponse = LoginResponse.builder()
                .token("exampleToken")
                .build();

        assertEquals(loginResponse.hashCode(), sameLoginResponse.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse.hashCode(), anotherLoginResponse.hashCode());
    }
}