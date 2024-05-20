package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new UserBuilder()
                .fullName("Ayam Sigma")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .gender(Gender.MALE)
                .username("ayamSigma")
                .email("ayam.sigma@gmail.com")
                .password("sigma")
                .role(Role.PEMBELI)
                .walletBalance(100.0)
                .build();
    }

    @Test
    void testGetFullName() {
        assertEquals("Ayam Sigma", user.getFullName());
    }

    @Test
    void testSetFullName() {
        user.setFullName("Sapi Sigma");
        assertEquals("Sapi Sigma", user.getFullName());
    }

    @Test
    void testGetDateOfBirth() {
        assertEquals(LocalDate.of(1990, 1, 1), user.getDateOfBirth());
    }

    @Test
    void testSetDateOfBirth() {
        user.setDateOfBirth(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), user.getDateOfBirth());
    }

    @Test
    void testGetGender() {
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    void testSetGender() {
        user.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, user.getGender());
    }

    @Test
    void testGetUsername() {
        assertEquals("ayam.sigma@gmail.com", user.getUsername());
    }

    @Test
    void testSetUsername() {
        user.setEmail("sapi.sigma@gmail.com");
        assertEquals("sapi.sigma@gmail.com", user.getUsername());
    }

    @Test
    void testGetRealUsername() {
        assertEquals("ayamSigma", user.getRealUsername());
    }

    @Test
    void testSetRealUsername() {
        user.setUsername("sapiSigma");
        assertEquals("sapiSigma", user.getRealUsername());
    }

    @Test
    void testGetEmail() {
        assertEquals("ayam.sigma@gmail.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        user.setEmail("sapi.sigma@gmail.com");
        assertEquals("sapi.sigma@gmail.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("sigma", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newsigma");
        assertEquals("newsigma", user.getPassword());
    }

    @Test
    void testGetRole() {
        assertEquals(Role.PEMBELI, user.getRole());
    }

    @Test
    void testSetRole() {
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testGetWalletBalance() {
        assertEquals(100.0, user.getWalletBalance());
    }

    @Test
    void testSetWalletBalance() {
        user.setWalletBalance(200.0);
        assertEquals(200.0, user.getWalletBalance());
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals(Role.PEMBELI.getValue(), authorities.iterator().next().getAuthority());
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
}
