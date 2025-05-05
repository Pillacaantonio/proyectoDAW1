package com.proyecto.controller;

import com.proyecto.dto.departamento.DepartamentoRequestDTO;
import com.proyecto.dto.departamento.DepartamentoResponseDTO;
import com.proyecto.dto.horario.HorarioRequestDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {
    private final DepartamentoService departamentoService;
    @GetMapping
    public List<DepartamentoResponseDTO> listarDepartamentos() {
        return departamentoService.listar();
    }
    @PostMapping
    public DepartamentoResponseDTO guardar(@RequestBody DepartamentoRequestDTO dto) {
        return departamentoService.guardar(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> eliminarDepartamento(@PathVariable Long id) {
        try {
             DepartamentoResponseDTO departamentoResponseDTO = departamentoService.eliminar(id);
            return ResponseEntity.ok(departamentoResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> actualizarDepartamento(
            @PathVariable Long id,
            @RequestBody DepartamentoRequestDTO departamentoRequestDTO) {
        departamentoRequestDTO.setDepartamentoId(id);
        DepartamentoResponseDTO departamentoResponseDTO = departamentoService.actualizar(departamentoRequestDTO);
        return ResponseEntity.ok(departamentoResponseDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        DepartamentoResponseDTO departamentoResponseDTO = departamentoService.buscarPorId(id);

        if (departamentoResponseDTO != null) {
            return ResponseEntity.ok(departamentoResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
