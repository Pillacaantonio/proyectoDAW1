package com.proyecto.entity;

import com.proyecto.auditoria.Auditable;
import com.proyecto.auditoria.AuditorListenerGenérico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditorListenerGenérico.class)

public class Horario  implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Double horas_asignadas;
    private Boolean estado;
    //Auditoria
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    private String usuarioCreacion;
    private String usuarioModifica;
}
