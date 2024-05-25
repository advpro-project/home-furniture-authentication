package com.hoomgroom.authentication.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.hoomgroom.authentication.dto.LoginRequest;
import com.hoomgroom.authentication.dto.LoginResponse;
import com.hoomgroom.authentication.dto.RegisterRequest;
import com.hoomgroom.authentication.model.Token;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.repository.TokenRepository;
import com.hoomgroom.authentication.repository.UserRepository;
import enums.Gender;
import enums.Role;
import enums.TokenType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Ayam Sigma");
        request.setDateOfBirth("1990-01-01");
        request.setGender("MALE");
        request.setUsername("ayamsigma");
        request.setEmail("ayamsigma@example.com");
        request.setPassword("password");
        request.setRole("ADMIN");

        User user = new User();
        user.setFullName(request.getFullName());
        user.setDateOfBirth(LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.setGender(Gender.valueOf(request.getGender()));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");
        user.setRole(Role.valueOf(request.getRole()));

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> {
            CompletableFuture<Void> future = authenticationService.register(request);
            future.join();
        });

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLogin() {
        LoginRequest request = new LoginRequest();
        request.setEmail("ayamsigma@example.com");
        request.setPassword("password");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");

        Token token = new Token();
        token.setUser(user);
        token.setJwtToken("jwtToken");
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(tokenRepository.save(any(Token.class))).thenReturn(token);

        CompletableFuture<LoginResponse> future = authenticationService.login(request);
        LoginResponse response = future.join();

        assertEquals("jwtToken", response.getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(jwtService, times(1)).generateToken(any(User.class));
        verify(tokenRepository, times(1)).save(any(Token.class));
    }
}
