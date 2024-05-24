package com.hoomgroom.authentication.dto;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {

    private UserData userData;

    @BeforeEach
    void setUp() {
        userData = UserData.builder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender(Gender.MALE)
                .username("ayamsigma")
                .email("ayamsigma@example.com")
                .role(Role.ADMIN)
                .walletBalance(100.0)
                .build();
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Ayam Sigma", userData.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), userData.getDateOfBirth());
        assertEquals(Gender.MALE, userData.getGender());
        assertEquals("ayamsigma", userData.getUsername());
        assertEquals("ayamsigma@example.com", userData.getEmail());
        assertEquals(Role.ADMIN, userData.getRole());
        assertEquals(100.0, userData.getWalletBalance());
    }

    @Test
    public void testSetters() {
        userData.setFullName("Ayam Omega");
        assertEquals("Ayam Omega", userData.getFullName());

        userData.setDateOfBirth(LocalDate.of(1995, 10, 20));
        assertEquals(LocalDate.of(1995, 10, 20), userData.getDateOfBirth());

        userData.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, userData.getGender());

        userData.setUsername("ayamomega");
        assertEquals("ayamomega", userData.getUsername());

        userData.setEmail("ayamomega@example.com");
        assertEquals("ayamomega@example.com", userData.getEmail());

        userData.setRole(Role.PEMBELI);
        assertEquals(Role.PEMBELI, userData.getRole());

        userData.setWalletBalance(200.0);
        assertEquals(200.0, userData.getWalletBalance());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(userData, userData);
    }

    @Test
    public void testEqualsDifferentObjectsWithSameValues() {
        UserData anotherUserData = UserData.builder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender(Gender.MALE)
                .username("ayamsigma")
                .email("ayamsigma@example.com")
                .role(Role.ADMIN)
                .walletBalance(100.0)
                .build();

        assertEquals(userData, anotherUserData);
    }

    @Test
    public void testEqualsDifferentObjectsWithDifferentValues() {
        UserData anotherUserData = UserData.builder()
                .fullName("Ayam Omega")
                .dateOfBirth(LocalDate.of(1995, 10, 20))
                .gender(Gender.FEMALE)
                .username("ayamomega")
                .email("ayamomega@example.com")
                .role(Role.PEMBELI)
                .walletBalance(200.0)
                .build();

        assertNotEquals(userData, anotherUserData);
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(userData.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        assertFalse(userData.equals("Not a UserData"));
    }

    @Test
    public void testHashCodeSameObjects() {
        UserData sameUserData = UserData.builder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender(Gender.MALE)
                .username("ayamsigma")
                .email("ayamsigma@example.com")
                .role(Role.ADMIN)
                .walletBalance(100.0)
                .build();

        assertEquals(userData.hashCode(), sameUserData.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        UserData anotherUserData = UserData.builder()
                .fullName("Ayam Omega")
                .dateOfBirth(LocalDate.of(1995, 10, 20))
                .gender(Gender.FEMALE)
                .username("ayamomega")
                .email("ayamomega@example.com")
                .role(Role.PEMBELI)
                .walletBalance(200.0)
                .build();

        assertNotEquals(userData.hashCode(), anotherUserData.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "UserData(fullName=Ayam Sigma, dateOfBirth=1990-05-15, gender=MALE, username=ayamsigma, email=ayamsigma@example.com, role=ADMIN, walletBalance=100.0)";
        assertEquals(expected, userData.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        UserData newUserData = new UserData();
        assertNull(newUserData.getFullName());
        assertNull(newUserData.getDateOfBirth());
        assertNull(newUserData.getGender());
        assertNull(newUserData.getUsername());
        assertNull(newUserData.getEmail());
        assertNull(newUserData.getRole());
        assertEquals(0.0, newUserData.getWalletBalance());
    }
}
