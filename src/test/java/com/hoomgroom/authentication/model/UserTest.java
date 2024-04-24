package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFullName("John Doe");
        user.setDateOfBirth(LocalDate.of(1990, 5, 15));
        user.setGender(Gender.MALE);
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password123");
        user.setRole(Role.GUEST);
        user.setWalletBalance(100.0);
    }

    @Test
    void testFullName() {
        assertEquals("John Doe", user.getFullName());
    }

    @Test
    void testDateOfBirth() {
        assertEquals(LocalDate.of(1990, 5, 15), user.getDateOfBirth());
    }

    @Test
    void testGender() {
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    void testUsername() {
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void testEmail() {
        assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    void testPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testRole() {
        assertEquals(Role.GUEST, user.getRole());
    }

    @Test
    void testWalletBalance() {
        assertEquals(100.0, user.getWalletBalance());
    }

    @Test
    void testSetFullName() {
        user.setFullName("Jane Doe");
        assertEquals("Jane Doe", user.getFullName());
    }

    @Test
    void testSetDateOfBirth() {
        user.setDateOfBirth(LocalDate.of(1985, 10, 20));
        assertEquals(LocalDate.of(1985, 10, 20), user.getDateOfBirth());
    }

    @Test
    void testSetGender() {
        user.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, user.getGender());
    }

    @Test
    void testSetUsername() {
        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());
    }

    @Test
    void testSetEmail() {
        user.setEmail("janedoe@example.com");
        assertEquals("janedoe@example.com", user.getEmail());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    @Test
    void testSetRole() {
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testSetWalletBalance() {
        user.setWalletBalance(200.0);
        assertEquals(200.0, user.getWalletBalance());
    }
}
