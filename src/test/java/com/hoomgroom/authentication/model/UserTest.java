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

class UserTest {

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
                new ArrayList<>()
        );
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Ayam Sigma", user.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), user.getDateOfBirth());
        assertEquals(Gender.MALE, user.getGender());
        assertEquals("ayamsigma", user.getRealUsername());
        assertEquals("ayamsigma@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
        assertNotNull(user.getTokens());
    }

    @Test
    void testSetters() {
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

        List<Token> tokens = new ArrayList<>();
        user.setTokens(tokens);
        assertEquals(tokens, user.getTokens());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(user, user);
    }

    @Test
    void testEqualsDifferentObjectsWithDifferentValues() {
        User anotherUser = new User(
                "Ayam Omega",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamomega",
                "ayamomega@example.com",
                "newpassword",
                Role.PEMBELI,
                new ArrayList<>()
        );

        assertNotEquals(user, anotherUser);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, user);
    }

    @Test
    void testEqualsWithDifferentType() {
        assertNotEquals("Not a User", user);
    }


    @Test
    void testHashCodeDifferentObjects() {
        User anotherUser = new User(
                "Ayam Omega",
                LocalDate.of(1995, 10, 20),
                Gender.FEMALE,
                "ayamomega",
                "ayamomega@example.com",
                "newpassword",
                Role.PEMBELI,
                new ArrayList<>()
        );

        assertNotEquals(user.hashCode(), anotherUser.hashCode());
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.ADMIN.getValue())));
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void testNoArgsConstructor() {
        User newUser = new User();
        assertNull(newUser.getFullName());
        assertNull(newUser.getDateOfBirth());
        assertNull(newUser.getGender());
        assertNull(newUser.getUsername());
        assertNull(newUser.getEmail());
        assertNull(newUser.getPassword());
        assertNull(newUser.getRole());
        assertNull(newUser.getTokens());
    }

    @Test
    void testUserBuilder() {
        UserBuilder builder = new UserBuilder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 5, 15))
                .gender(Gender.MALE)
                .username("ayamsigma")
                .email("ayamsigma@example.com")
                .password("password")
                .role(Role.ADMIN)
                .tokens(new ArrayList<>());

        User builtUser = builder.build();

        assertEquals("Ayam Sigma", builtUser.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), builtUser.getDateOfBirth());
        assertEquals(Gender.MALE, builtUser.getGender());
        assertEquals("ayamsigma", builtUser.getRealUsername());
        assertEquals("ayamsigma@example.com", builtUser.getEmail());
        assertEquals("password", builtUser.getPassword());
        assertEquals(Role.ADMIN, builtUser.getRole());
        assertNotNull(builtUser.getTokens());
    }
}
