package com.hoomgroom.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.authentication.service.UserDetailsImpl;
import com.hoomgroom.authentication.service.UserDetailsServiceImpl;
import com.hoomgroom.authentication.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User user;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
    }

    @Test
    void testAuthenticateUser() throws Exception {
        UserDetails userDetails = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(authenticationManager.authenticate(authRequest)).thenReturn(authentication);
        Mockito.when(userDetailsServiceImpl.loadUserByUsername(user.getUsername())).thenReturn(userDetails);

        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isString())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Authentication token: " + responseContent);
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
