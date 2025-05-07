package com.proyecto.service;

import com.proyecto.dto.auth.AuthRequestDTO;
import com.proyecto.dto.auth.AuthResponseDTO;
import com.proyecto.dto.auth.RefreshTokenRequestDTO;
import com.proyecto.entity.auth.Auth;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAutenticacionService {
    public AuthResponseDTO generarToken(AuthRequestDTO authRequestDTO);
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO dto);
}
