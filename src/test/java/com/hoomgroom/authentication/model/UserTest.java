package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                "Ayam Sigma",
                LocalDate.of(1990, 5, 15),
                Gender.MALE,
                "ayamsigma",
                "ayamsigma@example.com",
                "password",
                Role.ADMIN,
                100.0
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Ayam Sigma", user.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), user.getDateOfBirth());
        assertEquals(Gender.MALE, user.getGender());
        assertEquals("ayamsigma@example.com", user.getUsername());
        assertEquals("ayamsigma@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals(100.0, user.getWalletBalance());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(user, user);
    }

    @Test
    public void testEqualsDifferentObjectsWithSameValues() {
        User anotherUser = new User(
                "Ayam Sigma",
                LocalDate.of(1990, 5, 15),
                Gender.MALE,
                "ayamsigma",
                "ayamsigma@example.com",
                "password",
                Role.ADMIN,
                100.0
        );

        assertEquals(user, anotherUser);
    }

    @Test
    public void testEqualsDifferentObjectsWithDifferentValues() {
        User anotherUser = new User(
                "Ayam Sigma",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamsigma2",
                "ayamsigma2@example.com",
                "password123",
                Role.PEMBELI,
                200.0
        );

        assertNotEquals(user, anotherUser);
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(user.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        assertFalse(user.equals("Not a User"));
    }

    @Test
    public void testHashCodeSameObjects() {
        User sameUser = new User(
                "Ayam Sigma",
                LocalDate.of(1990, 5, 15),
                Gender.MALE,
                "ayamsigma",
                "ayamsigma@example.com",
                "password",
                Role.ADMIN,
                100.0
        );

        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        User anotherUser = new User(
                "Ayam Sigma",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamsigma2",
                "ayamsigma2@example.com",
                "password123",
                Role.PEMBELI,
                200.0
        );

        assertNotEquals(user.hashCode(), anotherUser.hashCode());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.ADMIN.getValue())));
    }

    @Test
    public void testGetUsername() {
        assertEquals("ayamsigma@example.com", user.getUsername());
    }

    @Test
    public void testGetRealUsername() {
        assertEquals("ayamsigma", user.getRealUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(user.isEnabled());
    }
}
