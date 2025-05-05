package com.proyecto.mapper;

import com.proyecto.dto.horario.HorarioRequestDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.entity.Horario;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {
    public HorarioResponseDTO toDTO(Horario horario) {
        return HorarioResponseDTO.builder()
                .id(horario.getId())
                .descripcion(horario.getDescripcion())
                .horas_asignadas(horario.getHoras_asignadas())
                .estado(horario.getEstado())
                .build();
    }
    public Horario toEntity(HorarioRequestDTO dto) {
        return  Horario.builder()
                .descripcion(dto.getDescripcion())
                .horas_asignadas(dto.getHoras_asignadas())
                .estado(dto.isEstado())
                .build();
    }
}
