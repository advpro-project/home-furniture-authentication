package com.hoomgroom.authentication.service;

import com.hoomgroom.authentication.model.Token;
import com.hoomgroom.authentication.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.autoconfigure.exclude=" +
        "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"})
class LogoutServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private LogoutService logoutService;

    @Test
    void testLogout() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Authentication authentication = mock(Authentication.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");

        Token token = mock(Token.class);
        when(tokenRepository.findByJwtToken(anyString())).thenReturn(java.util.Optional.of(token));

        logoutService.logout(request, response, authentication);

        verify(token, times(1)).setExpired(true);
        verify(token, times(1)).setRevoked(true);
        verify(tokenRepository, times(1)).save(token);
        verify(authentication, times(0)).setAuthenticated(false);
    }
}
