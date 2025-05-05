package com.proyecto.dto.empleado;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Data
@Component
public class EmpleadoRequestDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private Double sueldo;
    private Long departamentoId;
    private Long empleadoId;
    private Long horarioId;
}
