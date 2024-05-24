package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                100.0,
                new ArrayList<>()
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Ayam Sigma", user.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), user.getDateOfBirth());
        assertEquals(Gender.MALE, user.getGender());
        assertEquals("ayamsigma", user.getRealUsername());
        assertEquals("ayamsigma@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals(100.0, user.getWalletBalance());
        assertNotNull(user.getTokens());
    }

    @Test
    public void testSetters() {
        user.setFullName("Ayam Omega");
        assertEquals("Ayam Omega", user.getFullName());

        user.setDateOfBirth(LocalDate.of(1995, 10, 20));
        assertEquals(LocalDate.of(1995, 10, 20), user.getDateOfBirth());

        user.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, user.getGender());

        user.setUsername("ayamomega");
        assertEquals("ayamomega", user.getRealUsername());

        user.setEmail("ayamomega@example.com");
        assertEquals("ayamomega@example.com", user.getEmail());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        user.setRole(Role.PEMBELI);
        assertEquals(Role.PEMBELI, user.getRole());

        user.setWalletBalance(200.0);
        assertEquals(200.0, user.getWalletBalance());

        List<Token> tokens = new ArrayList<>();
        user.setTokens(tokens);
        assertEquals(tokens, user.getTokens());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(user, user);
    }

    @Test
    public void testEqualsDifferentObjectsWithDifferentValues() {
        User anotherUser = new User(
                "Ayam Omega",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamomega",
                "ayamomega@example.com",
                "newpassword",
                Role.PEMBELI,
                200.0,
                new ArrayList<>()
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
    public void testHashCodeDifferentObjects() {
        User anotherUser = new User(
                "Ayam Omega",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamomega",
                "ayamomega@example.com",
                "newpassword",
                Role.PEMBELI,
                200.0,
                new ArrayList<>()
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

    @Test
    public void testNoArgsConstructor() {
        User newUser = new User();
        assertNull(newUser.getFullName());
        assertNull(newUser.getDateOfBirth());
        assertNull(newUser.getGender());
        assertNull(newUser.getUsername());
        assertNull(newUser.getEmail());
        assertNull(newUser.getPassword());
        assertNull(newUser.getRole());
        assertEquals(0.0, newUser.getWalletBalance());
        assertNull(newUser.getTokens());
    }

    @Test
    public void testUserBuilder() {
        UserBuilder builder = new UserBuilder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender(Gender.MALE)
                .username("ayamsigma")
                .email("ayamsigma@example.com")
                .password("password")
                .role(Role.ADMIN)
                .walletBalance(100.0)
                .tokens(new ArrayList<>());

        User builtUser = builder.build();

        assertEquals("Ayam Sigma", builtUser.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), builtUser.getDateOfBirth());
        assertEquals(Gender.MALE, builtUser.getGender());
        assertEquals("ayamsigma", builtUser.getRealUsername());
        assertEquals("ayamsigma@example.com", builtUser.getEmail());
        assertEquals("password", builtUser.getPassword());
        assertEquals(Role.ADMIN, builtUser.getRole());
        assertEquals(100.0, builtUser.getWalletBalance());
        assertNotNull(builtUser.getTokens());
    }
}
