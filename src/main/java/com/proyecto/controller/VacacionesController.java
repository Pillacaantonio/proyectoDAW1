package com.proyecto.controller;

import com.proyecto.dto.vacaciones.VacacionesRequestDTO;
import com.proyecto.dto.vacaciones.VacacionesResponseDTO;
import com.proyecto.service.VacacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacaciones")
@RequiredArgsConstructor
public class VacacionesController {

    private final VacacionesService VacacionesService;

    @GetMapping
    public List<VacacionesResponseDTO> listar() {
        return VacacionesService.listar();
    }

    @PostMapping
    public VacacionesResponseDTO guardar(@RequestBody VacacionesRequestDTO dto) {
        return VacacionesService.crear(dto);
    }

    @GetMapping("/{id}")
    public VacacionesResponseDTO buscar(@PathVariable Long id) {
        return VacacionesService.obtenerPorId(id);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<VacacionesResponseDTO> actualizar(@PathVariable Long Id,
                                                                     @RequestBody VacacionesRequestDTO dto) {
        dto.setEmpleadoId(Id);

        VacacionesResponseDTO responseDTO = VacacionesService.actualizar(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}")
    public VacacionesResponseDTO eliminar(@PathVariable Long id) {
        return VacacionesService.eliminar(id);
    }
}