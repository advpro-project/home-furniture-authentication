package com.hoomgroom.authentication.config;

import com.hoomgroom.authentication.repository.UserRepository;

import enums.Role;
import enums.Gender;

import com.hoomgroom.authentication.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ApplicationConfigTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    private ApplicationConfig applicationConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        applicationConfig = new ApplicationConfig(userRepository);
    }

    @Test
    void testUserDetailsService_UserExists() {
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
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        UserDetailsService userDetailsService = applicationConfig.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername("ayamsigma@example.com");

        assertNotNull(userDetails);
        assertEquals("ayamsigma@example.com", userDetails.getUsername());
    }

    @Test
    void testUserDetailsService_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserDetailsService userDetailsService = applicationConfig.userDetailsService();

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonexistent@example.com");
        });
    }

    @Test
    void testAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

        assertNotNull(authenticationProvider);
        assertInstanceOf(DaoAuthenticationProvider.class, authenticationProvider);

        DaoAuthenticationProvider daoAuthenticationProvider = (DaoAuthenticationProvider) authenticationProvider;
        try {
            Field userDetailsServiceField = DaoAuthenticationProvider.class.getDeclaredField("userDetailsService");
            userDetailsServiceField.setAccessible(true);
            UserDetailsService userDetailsService = (UserDetailsService) userDetailsServiceField.get(daoAuthenticationProvider);
            assertNotNull(userDetailsService);

            Field passwordEncoderField = DaoAuthenticationProvider.class.getDeclaredField("passwordEncoder");
            passwordEncoderField.setAccessible(true);
            PasswordEncoder passwordEncoder = (PasswordEncoder) passwordEncoderField.get(daoAuthenticationProvider);
            assertNotNull(passwordEncoder);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    void testAuthenticationManager() throws Exception {
        AuthenticationManager mockManager = org.mockito.Mockito.mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager authenticationManager = applicationConfig.authenticationManager(authenticationConfiguration);

        assertNotNull(authenticationManager);
        assertEquals(mockManager, authenticationManager);
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
        assertInstanceOf(BCryptPasswordEncoder.class, passwordEncoder);
    }
}