package com.hoomgroom.authentication.dto;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    private LoginResponse loginResponse;

    @BeforeEach
    void setUp() {
        loginResponse = new LoginResponseBuilder()
                .token("exampleToken")
                .build();
    }

    @Test
    void testBuilder() {
        assertNotNull(loginResponse);
        assertEquals("exampleToken", loginResponse.getToken());
    }

    @Test
    void testSettersAndGetters() {
        loginResponse.setToken("newToken");
        assertEquals("newToken", loginResponse.getToken());
    }

    @Test
    void testAllArgsConstructor() {
        UserData userData = new UserDataBuilder()
                .fullName("dummyName")
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .username("dummyUsername")
                .email("dummyEmail@gmail.com")
                .role(Role.PEMBELI)
                .build();

        LoginResponse fullLoginResponse = new LoginResponse("exampleToken", userData);

        assertEquals("exampleToken", fullLoginResponse.getToken());
        assertEquals(userData, fullLoginResponse.getUserData());
    }

    @Test
    void testNoArgsConstructor() {
        LoginResponse noArgsLoginResponse = new LoginResponse();

        assertNull(noArgsLoginResponse.getToken());
        assertNull(noArgsLoginResponse.getUserData());

        noArgsLoginResponse.setToken("newToken");
        noArgsLoginResponse.setUserData(new UserData());

        assertEquals("newToken", noArgsLoginResponse.getToken());
        assertNotNull(noArgsLoginResponse.getUserData());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(loginResponse, loginResponse);
    }

    @Test
    void testEqualsDifferentObjectsWithDifferentValues() {
        LoginResponse anotherLoginResponse = new LoginResponseBuilder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse, anotherLoginResponse);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, loginResponse);
    }

    @Test
    void testEqualsWithDifferentType() {
        assertNotEquals("Not a LoginResponse", loginResponse);
    }

    @Test
    void testHashCodeDifferentObjects() {
        LoginResponse anotherLoginResponse = new LoginResponseBuilder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse.hashCode(), anotherLoginResponse.hashCode());
    }
}