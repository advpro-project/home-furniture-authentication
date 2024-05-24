package com.hoomgroom.authentication.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private UserData userData;
}
