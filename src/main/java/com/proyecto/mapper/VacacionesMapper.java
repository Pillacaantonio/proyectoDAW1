package com.proyecto.mapper;

import com.proyecto.dto.vacaciones.VacacionesRequestDTO;
import com.proyecto.dto.vacaciones.VacacionesResponseDTO;
import com.proyecto.entity.Vacaciones;
import org.springframework.stereotype.Component;

@Component
public class VacacionesMapper {
    public VacacionesResponseDTO toDTO(Vacaciones entity) {
        return VacacionesResponseDTO.builder()
                .id(entity.getId())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .motivo(entity.getMotivo())
                .empleadoId(entity.getEmpleado() != null ? entity.getEmpleado().getId() : null)
                .fechaCreacion(entity.getFechaCreacion())
                .fechaModifica(entity.getFechaModifica())
                .usuarioCreacion(entity.getUsuarioCreacion())
                .usuarioModifica(entity.getUsuarioModifica())
                .build();
    }

    public Vacaciones toEntity(VacacionesRequestDTO dto) {
        return Vacaciones.builder()
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .motivo(dto.getMotivo())
                .build();
    }
}
