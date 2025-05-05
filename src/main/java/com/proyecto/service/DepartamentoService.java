package com.proyecto.service;

import com.proyecto.dto.departamento.DepartamentoRequestDTO;
import com.proyecto.dto.departamento.DepartamentoResponseDTO;

import java.util.List;

public interface DepartamentoService {
    List<DepartamentoResponseDTO> listar();
    DepartamentoResponseDTO guardar (DepartamentoRequestDTO dto);
    DepartamentoResponseDTO actualizar (DepartamentoRequestDTO dto);
    DepartamentoResponseDTO eliminar (Long id);
    DepartamentoResponseDTO buscarPorId(Long id);
}
