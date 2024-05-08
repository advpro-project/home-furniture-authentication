package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<LoginResponse>> register(
            @RequestBody RegisterRequest request
    ) {
        return service.register(request)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<LoginResponse>> login(
            @RequestBody LoginRequest request
    ) {
        return service.login(request)
                .thenApply(ResponseEntity::ok);
    }
}