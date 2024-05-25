package com.hoomgroom.authentication.model;

import enums.TokenType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TokenTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.createQuery("DELETE FROM Token").executeUpdate();
    }

    @Test
    void testTokenEntity() {
        User user = new User();
        user.setEmail("ayamsigma@example.com");
        user.setFullName("Ayam Sigma");

        TokenBuilder builder = new TokenBuilder()
                .token("testToken")
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user);

        Token token =builder.build();

        entityManager.persist(user);
        entityManager.persist(token);
        entityManager.flush();

        Token foundToken = entityManager.find(Token.class, "testToken");

        assertNotNull(foundToken);
        assertEquals("testToken", foundToken.getJwtToken());
        assertEquals(TokenType.BEARER, foundToken.getTokenType());
        assertFalse(foundToken.isExpired());
        assertFalse(foundToken.isRevoked());
        assertEquals(user, foundToken.getUser());
    }
}
