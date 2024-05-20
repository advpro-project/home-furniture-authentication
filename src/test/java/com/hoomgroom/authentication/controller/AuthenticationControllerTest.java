package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.service.AuthenticationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        LoginResponse expectedResponse = new LoginResponse("dummyToken");

        when(authenticationService.register(registerRequest)).thenReturn(CompletableFuture.completedFuture(expectedResponse));

        ResponseEntity<LoginResponse> responseEntity = authenticationController.register(registerRequest).join();

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse expectedResponse = new LoginResponse("dummyToken");

        when(authenticationService.login(loginRequest)).thenReturn(CompletableFuture.completedFuture(expectedResponse));

        ResponseEntity<LoginResponse> responseEntity = authenticationController.login(loginRequest).join();

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
