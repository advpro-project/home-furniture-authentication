package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.dto.*;
import com.hoomgroom.authentication.model.TokenBuilder;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.model.UserBuilder;
import com.hoomgroom.authentication.repository.TokenRepository;
import com.hoomgroom.authentication.repository.UserRepository;

import enums.Gender;
import enums.Role;

import enums.TokenType;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public CompletableFuture<Void> register(RegisterRequest request) {
        return CompletableFuture.runAsync(() -> {
            var user = new UserBuilder()
                    .fullName(request.getFullName())
                    .dateOfBirth(LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .gender(Gender.valueOf(request.getGender()))
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf(request.getRole()))
                    .build();
            repository.save(user);
        });
    }

    @Transactional
    public CompletableFuture<LoginResponse> login(LoginRequest request) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
            } catch (AuthenticationException e) {
                throw new BadCredentialsException("Email atau password Anda salah", e);
            }

            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return new LoginResponseBuilder()
                    .token(jwtToken)
                    .userData(convertToUserData(user))
                    .build();
        });
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = new TokenBuilder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserToken = tokenRepository.findAllValidTokensByUserEmail(user.getEmail());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    private UserData convertToUserData(User user) {
        return new UserDataBuilder()
                .fullName(user.getFullName())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .username(user.getRealUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .walletBalance(user.getWalletBalance())
                .build();
    }
}
