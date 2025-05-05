package com.proyecto.entity;

import com.proyecto.auditoria.Auditable;
import com.proyecto.auditoria.AuditorListenerGenérico;
import com.proyecto.entity.enums.Documento;
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

public class Empleado  implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private Double sueldo;
    private Boolean estado = true;
    @Enumerated(EnumType.STRING)
    private Documento documento;
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    //Auditoria
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    private String usuarioCreacion;
    private String usuarioModifica;
}
