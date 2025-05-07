package com.proyecto.dto.empleado;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmpleadoResponseDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private Double sueldo;
    private Long departamentoId;
    private String descripcionDepartamento;
    private Long horarioId;
    private String descripcionHorario;
    private Boolean estado;
    //Vacaciones
    private int diasAcumulados;
    private int diasConsumidos;
    private int diasDisponibles;
}
