package com.proyecto.service.impl;

import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesRequestDTO;
import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesResponseDTO;
import com.proyecto.entity.CatalogoDiasNoLaborables;
import com.proyecto.mapper.CatalogoDiasNoLaborablesMapper;
import com.proyecto.repository.CatalogoDiasNoLaborablesRepository;
import com.proyecto.service.CatalogoDiasNoLaborablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogoDiasNoLaborablesServiceImpl implements CatalogoDiasNoLaborablesService {

    private final CatalogoDiasNoLaborablesRepository repository;
    private final CatalogoDiasNoLaborablesMapper mapper;

    @Override
    public List<CatalogoDiasNoLaborablesResponseDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public CatalogoDiasNoLaborablesResponseDTO crear(CatalogoDiasNoLaborablesRequestDTO dto) {
        CatalogoDiasNoLaborables entity = mapper.toEntity(dto);
        CatalogoDiasNoLaborables saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public CatalogoDiasNoLaborablesResponseDTO actualizar(Long id, CatalogoDiasNoLaborablesRequestDTO dto) {
        CatalogoDiasNoLaborables entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Día no laborable no encontrado"));

        entity.setFecha(dto.getFecha());
        entity.setDescripcion(dto.getDescripcion());

        CatalogoDiasNoLaborables updated = repository.save(entity);
        return mapper.toDTO(updated);
    }

    @Override
    public CatalogoDiasNoLaborablesResponseDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Día no laborable no encontrado"));
    }

    @Override
    public CatalogoDiasNoLaborablesResponseDTO eliminar(Long id) {
        CatalogoDiasNoLaborables entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Día no laborable no encontrado"));

        repository.delete(entity);
        return mapper.toDTO(entity);
    }
}
