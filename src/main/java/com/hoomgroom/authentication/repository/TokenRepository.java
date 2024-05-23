package com.hoomgroom.authentication.repository;

import com.hoomgroom.authentication.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    @Query("""
    SELECT t FROM Token t
    INNER JOIN User u ON t.user.email = u.email
    WHERE u.email = :userEmail AND (t.expired = false OR t.revoked = false)
    """)
    List<Token> findAllValidTokensByUserEmail(String userEmail);
    Optional<Token> findByToken(String token);
}
