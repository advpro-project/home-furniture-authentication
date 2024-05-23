package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.service.AuthenticationService;

import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();

        when(authenticationService.register(registerRequest)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<Void> responseEntity = authenticationController.register(registerRequest).join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testLogin() {
        LoginRequest loginRequest = new LoginRequest();

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

        LoginResponse expectedResponse = new LoginResponse("dummyToken", user);

        when(authenticationService.login(loginRequest)).thenReturn(CompletableFuture.completedFuture(expectedResponse));

        ResponseEntity<LoginResponse> responseEntity = authenticationController.login(loginRequest).join();

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
