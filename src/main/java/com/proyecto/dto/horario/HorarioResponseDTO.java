package com.proyecto.dto.horario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HorarioResponseDTO {
    private Long id;
    private String descripcion;
    private Double horas_asignadas;
    private Boolean estado;

}
