package com.proyecto.controller;

import com.proyecto.dto.vacaciones.SolicitudVacacionesRequestDTO;
import com.proyecto.dto.vacaciones.SolicitudVacacionesResponseDTO;
import com.proyecto.service.SolicitudVacacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes-vacaciones")
@RequiredArgsConstructor
public class SolicitudVacacionesController {

    private final SolicitudVacacionesService solicitudVacacionesService;

    @GetMapping
    public List<SolicitudVacacionesResponseDTO> listar() {
        return solicitudVacacionesService.listarSolicitudes();
    }

    @PostMapping
    public SolicitudVacacionesResponseDTO guardar(@RequestBody SolicitudVacacionesRequestDTO dto) {
        return solicitudVacacionesService.crearSolicitud(dto);
    }

    @GetMapping("/{id}")
    public SolicitudVacacionesResponseDTO buscar(@PathVariable Long id) {
        return solicitudVacacionesService.obtenerPorId(id);
    }

    @PutMapping("/{solicitudId}")
    public ResponseEntity<SolicitudVacacionesResponseDTO> actualizar(@PathVariable Long solicitudId,
                                                                     @RequestBody SolicitudVacacionesRequestDTO dto) {
        dto.setEmpleadoId(solicitudId);

        SolicitudVacacionesResponseDTO responseDTO = solicitudVacacionesService.actualizarSolicitud(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}")
    public SolicitudVacacionesResponseDTO eliminar(@PathVariable Long id) {
        return solicitudVacacionesService.eliminarSolicitud(id);
    }
}
