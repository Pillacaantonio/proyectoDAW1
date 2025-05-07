package com.proyecto.service;


import com.proyecto.dto.vacaciones.SolicitudVacacionesRequestDTO;
import com.proyecto.dto.vacaciones.SolicitudVacacionesResponseDTO;

import java.util.List;

public interface SolicitudVacacionesService {
    List<SolicitudVacacionesResponseDTO> listarSolicitudes();
    SolicitudVacacionesResponseDTO crearSolicitud(SolicitudVacacionesRequestDTO dto);
    SolicitudVacacionesResponseDTO actualizarSolicitud(SolicitudVacacionesRequestDTO dto);
    SolicitudVacacionesResponseDTO obtenerPorId(Long id);
    SolicitudVacacionesResponseDTO eliminarSolicitud(Long id);
}
