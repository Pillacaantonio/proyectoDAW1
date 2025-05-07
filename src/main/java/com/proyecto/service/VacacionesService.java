package com.proyecto.service;


import com.proyecto.dto.vacaciones.VacacionesRequestDTO;
import com.proyecto.dto.vacaciones.VacacionesResponseDTO;

import java.util.List;

public interface VacacionesService {
    List<VacacionesResponseDTO> listar();
    VacacionesResponseDTO crear(VacacionesRequestDTO dto);
    VacacionesResponseDTO actualizar(VacacionesRequestDTO dto);
    VacacionesResponseDTO obtenerPorId(Long id);
    VacacionesResponseDTO eliminar(Long id);
}
