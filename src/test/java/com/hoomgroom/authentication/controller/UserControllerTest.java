package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.UserData;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;
import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setFullName("Test User");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setGender(Gender.MALE);
        user.setUsername("testuser");
        user.setEmail(email);
        user.setRole(Role.PEMBELI);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<UserData> responseEntity = userController.getUserByEmail(email);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserData userData = responseEntity.getBody();
        assertEquals("Test User", userData.getFullName());
        assertEquals(LocalDate.of(1990, 1, 1), userData.getDateOfBirth());
        assertEquals(Gender.MALE, userData.getGender());
        assertEquals("testuser", userData.getUsername());
        assertEquals(email, userData.getEmail());
        assertEquals(Role.PEMBELI, userData.getRole());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<UserData> responseEntity = userController.getUserByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        UserData userData = responseEntity.getBody();
        assertNull(userData.getFullName());
        assertNull(userData.getDateOfBirth());
        assertNull(userData.getGender());
        assertNull(userData.getUsername());
        assertNull(userData.getEmail());
        assertNull(userData.getRole());
    }
}
