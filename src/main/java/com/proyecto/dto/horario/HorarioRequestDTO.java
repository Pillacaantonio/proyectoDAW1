package com.proyecto.dto.horario;

import lombok.Data;

@Data
public class HorarioRequestDTO {
    private String descripcion;
    private Double horas_asignadas;
    private boolean estado;
    private Long horarioId;


}
