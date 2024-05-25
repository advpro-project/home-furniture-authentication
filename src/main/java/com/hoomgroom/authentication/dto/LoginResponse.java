package com.hoomgroom.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private UserData userData;

    public LoginResponse(LoginResponseBuilder builder) {
        this.token = builder.token;
        this.userData = builder.userData;
    }
}
