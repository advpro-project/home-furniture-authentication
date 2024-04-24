package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindByUsername() {
        String username = "john";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername(username);
        assertTrue(foundUser.isPresent());
        assertEquals(username, foundUser.get().getUsername());

        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testSaveUser_Success() {
        String username = "john";
        String email = "john@example.com";
        String password = "password";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(user);

        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(username, savedUser.getUsername());
        assertEquals(email, savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testSaveUser_Failure_ExistingUsername() {
        String username = "john";

        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User savedUser = userService.saveUser(user);
        assertNull(savedUser);

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, never()).findByEmail(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testSaveUser_Failure_ExistingEmail() {
        String email = "john@example.com";

        User user = new User();
        user.setEmail(email);

        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User savedUser = userService.saveUser(user);
        assertNull(savedUser);

        verify(userRepository, never()).findByUsername(any());
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testAuthenticate_Success() {
        String username = "john";
        String password = "password";
        String encodedPassword = "encodedPassword";

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        assertTrue(userService.authenticate(username, password));

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }

    @Test
    void testAuthenticate_Failure_InvalidUsername() {
        assertFalse(userService.authenticate(null, "password"));
        assertFalse(userService.authenticate("", "password"));

        verify(userRepository, never()).findByUsername(any());
        verify(passwordEncoder, never()).matches(any(), any());
    }

    @Test
    void testAuthenticate_Failure_InvalidPassword() {
        assertFalse(userService.authenticate("john", null));
        assertFalse(userService.authenticate("john", ""));

        verify(userRepository, never()).findByUsername(any());
        verify(passwordEncoder, never()).matches(any(), any());
    }

    @Test
    void testAuthenticate_Failure_UserNotFound() {
        String username = "john";
        String password = "password";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertFalse(userService.authenticate(username, password));

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, never()).matches(any(), any());
    }

    @Test
    void testAuthenticate_Failure_PasswordMismatch() {
        String username = "john";
        String password = "password";
        String encodedPassword = "encodedPassword";

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        assertFalse(userService.authenticate(username, password));

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }

    @Test
    void testIsValidUser_True() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        assertTrue(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_NullUser() {
        assertFalse(userService.isValidUser(null));
    }

    @Test
    void testIsValidUser_False_NullUsername() {
        User user = new User();
        user.setEmail("john@example.com");
        user.setPassword("password");

        assertFalse(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_EmptyUsername() {
        User user = new User();
        user.setUsername("");
        user.setEmail("john@example.com");
        user.setPassword("password");

        assertFalse(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_NullEmail() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");

        assertFalse(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_EmptyEmail() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("");
        user.setPassword("password");

        assertFalse(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_NullPassword() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");

        assertFalse(userService.isValidUser(user));
    }

    @Test
    void testIsValidUser_False_EmptyPassword() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("");

        assertFalse(userService.isValidUser(user));
    }
}
