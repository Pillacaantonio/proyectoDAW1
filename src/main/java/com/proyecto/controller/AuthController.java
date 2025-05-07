package com.proyecto.controller;

import com.proyecto.dto.auth.AuthRequestDTO;
import com.proyecto.dto.auth.AuthResponseDTO;
import com.proyecto.dto.auth.RefreshTokenRequestDTO;
import com.proyecto.service.IAutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAutenticacionService autenticacionService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(autenticacionService.generarToken(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO dto) {
        return ResponseEntity.ok(autenticacionService.refreshToken(dto));
    }
}
