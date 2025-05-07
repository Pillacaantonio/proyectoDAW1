package com.proyecto.mapper;

import com.proyecto.dto.vacaciones.SolicitudVacacionesRequestDTO;
import com.proyecto.dto.vacaciones.SolicitudVacacionesResponseDTO;
import com.proyecto.entity.SolicitudVacaciones;
import org.springframework.stereotype.Component;

@Component
public class SolicitudVacacionesMapper {
    public SolicitudVacacionesResponseDTO toDTO(SolicitudVacaciones entity) {
        return SolicitudVacacionesResponseDTO.builder()
                .id(entity.getId())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .motivo(entity.getMotivo())
                .estado(entity.getEstado())  // Aquí mapea el enum como texto
                .empleadoId(entity.getEmpleado() != null ? entity.getEmpleado().getId() : null)
                .fechaCreacion(entity.getFechaCreacion())
                .fechaModifica(entity.getFechaModifica())
                .usuarioCreacion(entity.getUsuarioCreacion())
                .usuarioModifica(entity.getUsuarioModifica())
                .build();
    }

    public SolicitudVacaciones toEntity(SolicitudVacacionesRequestDTO dto) {
        return SolicitudVacaciones.builder()
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .motivo(dto.getMotivo())
                .estado(dto.getEstado())  // Aquí deberías asegurarte que el DTO mande el enum correcto
                .build();
    }
}
