package com.proyecto.mapper;

import com.proyecto.dto.empleado.EmpleadoRequestDTO;
import com.proyecto.dto.empleado.EmpleadoResponseDTO;
import com.proyecto.entity.Departamento;
import com.proyecto.entity.Empleado;
import com.proyecto.entity.Horario;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    public EmpleadoResponseDTO toDTO(Empleado empleado) {
        return  EmpleadoResponseDTO.builder()
                .id(empleado.getId())
                .nombre(empleado.getNombre())
                .apellidoMaterno(empleado.getApellidoMaterno())
                .apellidoPaterno(empleado.getApellidoPaterno())
                .email(empleado.getEmail())
                .sueldo(empleado.getSueldo())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .telefono(empleado.getTelefono())
                .direccion(empleado.getDireccion())
                .departamentoId(empleado.getDepartamento().getId())
                .descripcionDepartamento(empleado.getDepartamento().getDescripcion())
                .horarioId(empleado.getHorario().getId())
                .descripcionHorario(empleado.getHorario().getDescripcion())
                .estado(empleado.getEstado())
                .build();
    }
    public Empleado toEntity(EmpleadoRequestDTO dto , Departamento departamento, Horario horario) {
        return Empleado.builder()
                .nombre(dto.getNombre())
                .apellidoMaterno(dto.getApellidoMaterno())
                .apellidoPaterno(dto.getApellidoPaterno())
                .email(dto.getEmail())
                .sueldo(dto.getSueldo())
                .fechaNacimiento(dto.getFechaNacimiento())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .departamento(departamento)
                .horario(horario)
                .build();

    }
}
