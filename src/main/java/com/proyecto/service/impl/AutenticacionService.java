package com.proyecto.service.impl;

import com.proyecto.dto.auth.AuthRequestDTO;
import com.proyecto.dto.auth.AuthResponseDTO;
import com.proyecto.dto.auth.RefreshTokenRequestDTO;
import com.proyecto.entity.auth.Auth;
import com.proyecto.entity.auth.UserEntity;
import com.proyecto.mapper.AuthMapper;
import com.proyecto.service.IAutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutenticacionService implements IAutenticacionService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private UserDetailsService userDetailsService;

    private final AuthMapper mapper;

    @Override
    public AuthResponseDTO generarToken(AuthRequestDTO authRequestDTO) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        Instant now = Instant.now();
        Instant expireAt = now.plus(8, ChronoUnit.HOURS);
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(authRequestDTO.getUsername())
                .issuedAt(now)
                .expiresAt(expireAt)
                .issuer("proyectoDAW1")
                .claim("scope", scope)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return mapper.toDTO(null, token, authentication, expireAt); // Pasa null o ajusta el mapper
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO dto) {
        if (dto.getRefreshToken() == null || dto.getRefreshToken().isBlank()) {
            throw new RuntimeException("El refresh token es requerido.");
        }

        Jwt decodedJwt;
        try {
            decodedJwt = jwtDecoder.decode(dto.getRefreshToken());
        } catch (Exception e) {
            throw new RuntimeException("Refresh token inválido: " + e.getMessage());
        }

        String username = decodedJwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String scope = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant expireAt = now.plus(8, ChronoUnit.HOURS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(now)
                .expiresAt(expireAt)
                .issuer("proyectoDAW1")
                .claim("scope", scope)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return mapper.toDTO((UserEntity) userDetails, token, null, expireAt);
    }

}
