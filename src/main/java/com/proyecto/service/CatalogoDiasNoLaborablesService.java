package com.proyecto.service;

import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesRequestDTO;
import com.proyecto.dto.catalogo.CatalogoDiasNoLaborablesResponseDTO;

import java.util.List;

public interface CatalogoDiasNoLaborablesService {
    List<CatalogoDiasNoLaborablesResponseDTO> listar();
    CatalogoDiasNoLaborablesResponseDTO crear(CatalogoDiasNoLaborablesRequestDTO dto);
    CatalogoDiasNoLaborablesResponseDTO actualizar(Long id, CatalogoDiasNoLaborablesRequestDTO dto);
    CatalogoDiasNoLaborablesResponseDTO obtenerPorId(Long id);
    CatalogoDiasNoLaborablesResponseDTO eliminar(Long id);
}
