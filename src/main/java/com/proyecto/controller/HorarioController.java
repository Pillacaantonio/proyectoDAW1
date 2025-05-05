package com.proyecto.controller;

import com.proyecto.dto.horario.HorarioRequestDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping
    public List<HorarioResponseDTO> listarHorarios() {
        return horarioService.listar();
    }
    @PostMapping
    public HorarioResponseDTO guardar(@RequestBody HorarioRequestDTO dto) {
        return horarioService.guardar(dto);
    }
    @PutMapping("/{horarioId}")
    public ResponseEntity<HorarioResponseDTO> actualizarHorario(
            @PathVariable Long horarioId,
            @RequestBody HorarioRequestDTO horarioRequestDTO) {
         horarioRequestDTO.setHorarioId(horarioId);
        HorarioResponseDTO horarioResponseDTO = horarioService.actualizarHorario(horarioRequestDTO);
        return ResponseEntity.ok(horarioResponseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        HorarioResponseDTO horarioResponseDTO = horarioService.obtenerPorId(id);
        if (horarioResponseDTO != null) {
            return ResponseEntity.ok(horarioResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> eliminarHorario(@PathVariable Long id) {
        try {
            HorarioResponseDTO horarioResponseDTO = horarioService.eliminar(id);
            return ResponseEntity.ok(horarioResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
