package com.hoomgroom.authentication.model;

import enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token implements Serializable {
    @Id
    private String jwtToken;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    Token(TokenBuilder builder) {
        this.jwtToken = builder.token;
        this.tokenType = builder.tokenType;
        this.expired = builder.expired;
        this.revoked = builder.revoked;
        this.user = builder.user;
    }
}
