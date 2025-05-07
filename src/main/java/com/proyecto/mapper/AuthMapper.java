package com.proyecto.mapper;

import com.proyecto.dto.auth.AuthRequestDTO;
import com.proyecto.dto.auth.AuthResponseDTO;
import com.proyecto.entity.auth.Auth;
import com.proyecto.entity.auth.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class AuthMapper {

    public Auth toAuth(AuthRequestDTO dto) {
        return Auth.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .grantType("password")
                .withRefreshToken(true).build();
    }
    public AuthResponseDTO toDTO(UserEntity userEntity, String token, Authentication authentication, Instant expireAt){
        return AuthResponseDTO.builder()
                .token(token)
                .username(authentication.getName())
                .roles(authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .expireAt(expireAt.getEpochSecond())
                .build();
    }

}
