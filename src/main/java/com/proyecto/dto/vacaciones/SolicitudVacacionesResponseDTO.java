package com.proyecto.dto.vacaciones;

import com.proyecto.entity.enums.EstadoSolicitudVacaciones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudVacacionesResponseDTO {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
    private EstadoSolicitudVacaciones estado;
    private Long empleadoId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    private String usuarioCreacion;
    private String usuarioModifica;
}
