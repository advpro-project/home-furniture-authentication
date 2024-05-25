package com.hoomgroom.authentication.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequestBuilder()
                .fullName("Ayam Sigma")
                .dateOfBirth("1990-01-01")
                .gender("MALE")
                .username("ayamSigma")
                .email("ayamsigma@gmail.com")
                .password("ayamsigma@gmail.com")
                .role("PEMBELI")
                .build();
    }

    @Test
    void testBuilder() {
        assertNotNull(registerRequest);
        assertEquals("Ayam Sigma", registerRequest.getFullName());
        assertEquals("1990-01-01", registerRequest.getDateOfBirth());
        assertEquals("MALE", registerRequest.getGender());
        assertEquals("ayamSigma", registerRequest.getUsername());
        assertEquals("ayamsigma@gmail.com", registerRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", registerRequest.getPassword());
        assertEquals("PEMBELI", registerRequest.getRole());
    }

    @Test
    void testSettersAndGetters() {
        registerRequest.setFullName("Sapi Sigma");
        assertEquals("Sapi Sigma", registerRequest.getFullName());

        registerRequest.setDateOfBirth("1985-05-05");
        assertEquals("1985-05-05", registerRequest.getDateOfBirth());

        registerRequest.setGender("FEMALE");
        assertEquals("FEMALE", registerRequest.getGender());

        registerRequest.setUsername("sapiSigma");
        assertEquals("sapiSigma", registerRequest.getUsername());

        registerRequest.setEmail("sapisigma@gmail.com");
        assertEquals("sapisigma@gmail.com", registerRequest.getEmail());

        registerRequest.setPassword("newpassword");
        assertEquals("newpassword", registerRequest.getPassword());

        registerRequest.setRole("ADMIN");
        assertEquals("ADMIN", registerRequest.getRole());
    }

    @Test
    void testNoArgsConstructor() {
        RegisterRequest emptyRegisterRequest = new RegisterRequest();

        assertNull(emptyRegisterRequest.getFullName());
        assertNull(emptyRegisterRequest.getDateOfBirth());
        assertNull(emptyRegisterRequest.getGender());
        assertNull(emptyRegisterRequest.getUsername());
        assertNull(emptyRegisterRequest.getEmail());
        assertNull(emptyRegisterRequest.getPassword());
        assertNull(emptyRegisterRequest.getRole());
    }

    @Test
    void testAllArgsConstructor() {
        RegisterRequest fullRegisterRequest = new RegisterRequest(
                "Ayam Sigma",
                "1990-01-01",
                "MALE",
                "ayamSigma",
                "ayamsigma@gmail.com",
                "ayamsigma@gmail.com",
                "PEMBELI"
        );

        assertEquals("Ayam Sigma", fullRegisterRequest.getFullName());
        assertEquals("1990-01-01", fullRegisterRequest.getDateOfBirth());
        assertEquals("MALE", fullRegisterRequest.getGender());
        assertEquals("ayamSigma", fullRegisterRequest.getUsername());
        assertEquals("ayamsigma@gmail.com", fullRegisterRequest.getEmail());
        assertEquals("ayamsigma@gmail.com", fullRegisterRequest.getPassword());
        assertEquals("PEMBELI", fullRegisterRequest.getRole());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(registerRequest, registerRequest);
    }

    @Test
    void testEqualsDifferentObjectsWithDifferentValues() {
        RegisterRequest anotherRegisterRequest = new RegisterRequestBuilder()
                .fullName("Ayam Kappa")
                .dateOfBirth("1991-01-01")
                .gender("FEMALE")
                .username("ayamKappa")
                .email("ayamkappa@gmail.com")
                .password("ayamkappa@gmail.com")
                .role("ADMIN")
                .build();

        assertNotEquals(registerRequest, anotherRegisterRequest);
    }

    @Test
    void testEqualsWithNull() {
        assertFalse(registerRequest.equals(null));
    }

    @Test
    void testEqualsWithDifferentType() {
        assertFalse(registerRequest.equals("Not a RegisterRequest"));
    }

    @Test
    void testHashCodeConsistency() {
        int initialHashCode = registerRequest.hashCode();
        assertEquals(initialHashCode, registerRequest.hashCode());
    }

    @Test
    void testHashCodeForDifferentObjects() {
        RegisterRequest anotherRegisterRequest = new RegisterRequestBuilder()
                .fullName("Ayam Kappa")
                .dateOfBirth("1991-01-01")
                .gender("FEMALE")
                .username("ayamKappa")
                .email("ayamkappa@gmail.com")
                .password("ayamkappa@gmail.com")
                .role("ADMIN")
                .build();

        assertNotEquals(registerRequest.hashCode(), anotherRegisterRequest.hashCode());
    }
}
