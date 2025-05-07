package com.proyecto.entity;

import com.proyecto.auditoria.Auditable;
import com.proyecto.auditoria.AuditorListenerGenérico;
import com.proyecto.entity.enums.Documento;
import com.proyecto.entity.enums.EstadoSolicitudVacaciones;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditorListenerGenérico.class)

public class SolicitudVacaciones implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
    @Enumerated(EnumType.STRING)
    private EstadoSolicitudVacaciones estado;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
    //Auditoria
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    private String usuarioCreacion;
    private String usuarioModifica;
}
