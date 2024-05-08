package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.config.JwtService;
import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.UserRepository;
import enums.Gender;
import enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public CompletableFuture<LoginResponse> register(RegisterRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            var user = User.builder()
                    .fullName(request.getFullName())
                    .dateOfBirth(LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .gender(Gender.valueOf(request.getGender()))
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf(request.getRole()))
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return LoginResponse.builder()
                    .token(jwtToken)
                    .build();
        });
    }

    @Transactional
    public CompletableFuture<LoginResponse> login(LoginRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return LoginResponse.builder()
                    .token(jwtToken)
                    .build();
        });
    }
}
