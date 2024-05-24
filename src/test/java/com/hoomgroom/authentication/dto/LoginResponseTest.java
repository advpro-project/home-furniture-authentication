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
        loginResponse = LoginResponse.builder()
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
        UserData userData = UserData.builder()
                .fullName("dummyName")
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .username("dummyUsername")
                .email("dummyEmail@gmail.com")
                .role(Role.PEMBELI)
                .walletBalance(0.0)
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
    void testEqualsDifferentObjectsWithSameValues() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("exampleToken")
                .build();

        assertEquals(loginResponse, anotherLoginResponse);
    }

    @Test
    void testEqualsDifferentObjectsWithDifferentValues() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse, anotherLoginResponse);
    }

    @Test
    void testEqualsWithNull() {
        assertFalse(loginResponse.equals(null));
    }

    @Test
    void testEqualsWithDifferentType() {
        assertFalse(loginResponse.equals("Not a LoginResponse"));
    }

    @Test
    void testHashCodeSameObjects() {
        LoginResponse sameLoginResponse = LoginResponse.builder()
                .token("exampleToken")
                .build();

        assertEquals(loginResponse.hashCode(), sameLoginResponse.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        LoginResponse anotherLoginResponse = LoginResponse.builder()
                .token("newToken")
                .build();

        assertNotEquals(loginResponse.hashCode(), anotherLoginResponse.hashCode());
    }

    @Test
    void testToString() {
        UserData userData = UserData.builder()
                .fullName("dummyName")
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .username("dummyUsername")
                .email("dummyEmail@gmail.com")
                .role(Role.PEMBELI)
                .walletBalance(0.0)
                .build();

        LoginResponse fullLoginResponse = new LoginResponse("exampleToken", userData);
        String expectedToString = "LoginResponse(token=exampleToken, userData=" + userData.toString() + ")";
        assertEquals(expectedToString, fullLoginResponse.toString());
    }
}