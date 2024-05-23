package com.hoomgroom.authentication.model;

import enums.TokenType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TokenTest {

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

        Token token = Token.builder()
                .token("testToken")
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user)
                .build();

        entityManager.persist(user);
        entityManager.persist(token);
        entityManager.flush();

        Token foundToken = entityManager.find(Token.class, "testToken");

        assertNotNull(foundToken);
        assertEquals("testToken", foundToken.getToken());
        assertEquals(TokenType.BEARER, foundToken.getTokenType());
        assertEquals(false, foundToken.isExpired());
        assertEquals(false, foundToken.isRevoked());
        assertEquals(user, foundToken.getUser());
    }

    @Test
    void testEquals() {
        Token token1 = new Token("token1", TokenType.BEARER, false, false, null);
        Token token2 = new Token("token1", TokenType.BEARER, false, false, null);
        Token token3 = new Token("token2", TokenType.BEARER, false, false, null);

        assertEquals(token1, token2);
        assertNotEquals(token1, token3);
    }

    @Test
    void testHashCode() {
        Token token1 = new Token("token1", TokenType.BEARER, false, false, null);
        Token token2 = new Token("token1", TokenType.BEARER, false, false, null);
        Token token3 = new Token("token2", TokenType.BEARER, false, false, null);

        assertEquals(token1.hashCode(), token2.hashCode());
        assertNotEquals(token1.hashCode(), token3.hashCode());
    }

    @Test
    void testToString() {
        Token token = new Token("token1", TokenType.BEARER, false, false, null);
        String expectedToString = "Token(token=token1, tokenType=BEARER, expired=false, revoked=false, user=null)";

        assertEquals(expectedToString, token.toString());
    }
}
