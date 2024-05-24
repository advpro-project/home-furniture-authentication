package com.hoomgroom.authentication.controller;

import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.model.UserBuilder;
import com.hoomgroom.authentication.repository.TokenRepository;
import com.hoomgroom.authentication.service.JwtService;
import enums.Gender;
import enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private TokenRepository tokenRepository;

    private String jwtToken;

    @BeforeEach
    void setUp() {
        User user = new UserBuilder()
                .fullName("dummyName")
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .username("dummyUsername")
                .email("dummyEmail@gmail.com")
                .password("password")
                .role(Role.PEMBELI)
                .walletBalance(0.0)
                .tokens(new ArrayList<>())
                .build();

        jwtToken = "Bearer " + jwtService.generateToken(user);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);
    }

    @Test
    @WithMockUser
    void testHelloWithValidToken() throws Exception {
        mockMvc.perform(get("/demo")
                        .header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void testHelloWithoutToken() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testHelloWithInvalidToken() throws Exception {
        mockMvc.perform(get("/demo")
                        .header("Authorization", "Bearer invalidToken"))
                .andExpect(status().isUnauthorized());
    }
}
