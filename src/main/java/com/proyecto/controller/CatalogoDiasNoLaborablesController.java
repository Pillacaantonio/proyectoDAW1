package com.proyecto.controller;

import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesRequestDTO;
import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesResponseDTO;
import com.proyecto.service.CatalogoDiasNoLaborablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo-dias-no-laborables")
@RequiredArgsConstructor
public class CatalogoDiasNoLaborablesController {

    private final CatalogoDiasNoLaborablesService service;

    @GetMapping
    public List<CatalogoDiasNoLaborablesResponseDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public CatalogoDiasNoLaborablesResponseDTO crear(@RequestBody CatalogoDiasNoLaborablesRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public CatalogoDiasNoLaborablesResponseDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoDiasNoLaborablesResponseDTO> actualizar(@PathVariable Long id,
                                                                          @RequestBody CatalogoDiasNoLaborablesRequestDTO dto) {
        CatalogoDiasNoLaborablesResponseDTO updated = service.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public CatalogoDiasNoLaborablesResponseDTO eliminar(@PathVariable Long id) {
        return service.eliminar(id);
    }
}
