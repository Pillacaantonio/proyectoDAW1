package com.proyecto.dto.departamento;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DepartamentoResponseDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    private String usuarioCreacion;
    private String usuarioModifica;
}

