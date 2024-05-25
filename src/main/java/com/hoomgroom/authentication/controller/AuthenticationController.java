package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<Void>> register(
            @RequestBody RegisterRequest request
    ) {
        return service.register(request)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> {
                    if (e.getCause() instanceof RuntimeException) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    }
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<LoginResponse>> login(
            @RequestBody LoginRequest request
    ) {
        return service.login(request)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> {
                    if (e.getCause() instanceof BadCredentialsException) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}