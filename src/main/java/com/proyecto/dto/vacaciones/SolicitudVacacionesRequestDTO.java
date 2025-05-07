package com.proyecto.dto.vacaciones;

import com.proyecto.entity.enums.EstadoSolicitudVacaciones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudVacacionesRequestDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
    private EstadoSolicitudVacaciones estado;
    private Long empleadoId;
}

