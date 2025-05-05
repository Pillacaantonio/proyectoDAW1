package com.proyecto.mapper;

import com.proyecto.dto.departamento.DepartamentoRequestDTO;
import com.proyecto.dto.departamento.DepartamentoResponseDTO;
import com.proyecto.entity.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {

     public DepartamentoResponseDTO toDTO(Departamento departamento) {
        return DepartamentoResponseDTO.builder()
                .id(departamento.getId())
                .descripcion(departamento.getDescripcion())
                .estado(departamento.getEstado())
                .fechaCreacion(departamento.getFechaCreacion())
                .fechaModifica(departamento.getFechaModifica())
                .usuarioCreacion(departamento.getUsuarioCreacion())
                .usuarioModifica(departamento.getUsuarioModifica())
                .build();
    }

     public Departamento toEntity(DepartamentoRequestDTO dto) {
        return Departamento.builder()
                .descripcion(dto.getDescripcion())
                .estado(dto.isEstado())
                .build();
    }
}

