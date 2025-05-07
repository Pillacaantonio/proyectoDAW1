package com.proyecto.entity.auth;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class Auth {
    private String grantType;
    private  String username;
    private  String password;
    private boolean withRefreshToken;
    private String refresToken;
}