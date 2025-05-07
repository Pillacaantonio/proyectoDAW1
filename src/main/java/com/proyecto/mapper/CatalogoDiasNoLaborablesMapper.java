package com.proyecto.mapper;

import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesRequestDTO;
import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesResponseDTO;
import com.proyecto.entity.CatalogoDiasNoLaborables;
import org.springframework.stereotype.Component;

@Component
public class CatalogoDiasNoLaborablesMapper {
    public CatalogoDiasNoLaborablesResponseDTO toDTO(CatalogoDiasNoLaborables entity) {
        return CatalogoDiasNoLaborablesResponseDTO.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public CatalogoDiasNoLaborables toEntity(CatalogoDiasNoLaborablesRequestDTO dto) {
        return CatalogoDiasNoLaborables.builder()
                .fecha(dto.getFecha())
                .descripcion(dto.getDescripcion())
                .build();
    }
}