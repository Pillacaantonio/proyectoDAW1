package com.proyecto.service.impl;

import com.proyecto.dto.empleado.EmpleadoRequestDTO;
import com.proyecto.dto.empleado.EmpleadoResponseDTO;
import com.proyecto.entity.Departamento;
import com.proyecto.entity.Empleado;
import com.proyecto.entity.Horario;
import com.proyecto.mapper.EmpleadoMapper;
import com.proyecto.repository.DepartamentoRepository;
import com.proyecto.repository.EmpleadoRepository;
import com.proyecto.repository.HorarioRepository;
import com.proyecto.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final HorarioRepository horarioRepository;
    private final EmpleadoRequestDTO empleadoRequestDTO;
    private final EmpleadoMapper empleadoMapper;


    @Override
    public List<EmpleadoResponseDTO> listarEmpleados() {
       return empleadoRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO dto) {
        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

        Horario horario = horarioRepository.findById(dto.getHorarioId())
                .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado"));

        Empleado empleado = Empleado.builder()
                .nombre(dto.getNombre())
                .apellidoMaterno(dto.getApellidoMaterno())
                .apellidoPaterno(dto.getApellidoPaterno())
                .fechaNacimiento(dto.getFechaNacimiento())
                .direccion(dto.getDireccion())
                .email(dto.getEmail())
                .sueldo(dto.getSueldo())
                .telefono(dto.getTelefono())
                .horario(horario)
                .departamento(departamento)
                .estado(true)
                .build();
        return  toDTO(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoResponseDTO actualizarEmpleado(EmpleadoRequestDTO empleadoRequestDTO) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(empleadoRequestDTO.getEmpleadoId());

        if (empleadoOptional.isPresent()) {
            Empleado emp = empleadoOptional.get();

             emp.setNombre(empleadoRequestDTO.getNombre());
            emp.setApellidoPaterno(empleadoRequestDTO.getApellidoPaterno());
            emp.setApellidoMaterno(empleadoRequestDTO.getApellidoMaterno());
            emp.setEmail(empleadoRequestDTO.getEmail());
            emp.setTelefono(empleadoRequestDTO.getTelefono());
            emp.setDireccion(empleadoRequestDTO.getDireccion());
            emp.setFechaNacimiento(empleadoRequestDTO.getFechaNacimiento());
            emp.setSueldo(empleadoRequestDTO.getSueldo());

             if (empleadoRequestDTO.getDepartamentoId() != null) {
                Departamento departamento = departamentoRepository.findById(empleadoRequestDTO.getDepartamentoId())
                        .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
                emp.setDepartamento(departamento);
            }

             if (empleadoRequestDTO.getHorarioId() != null) {
                Horario horario = horarioRepository.findById(empleadoRequestDTO.getHorarioId())
                        .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
                emp.setHorario(horario);
            }

             empleadoRepository.save(emp);

             return EmpleadoResponseDTO.builder()
                    .id(emp.getId())
                    .nombre(emp.getNombre())
                    .apellidoPaterno(emp.getApellidoPaterno())
                    .apellidoMaterno(emp.getApellidoMaterno())
                    .email(emp.getEmail())
                    .telefono(emp.getTelefono())
                    .direccion(emp.getDireccion())
                    .fechaNacimiento(emp.getFechaNacimiento())
                    .sueldo(emp.getSueldo())
                    .departamentoId(emp.getDepartamento().getId())
                    .descripcionDepartamento(emp.getDepartamento().getDescripcion())
                    .horarioId(emp.getHorario().getId())
                    .descripcionHorario(emp.getHorario().getDescripcion())
                    .estado(emp.getEstado()) // Mantener el estado original si no cambia
                    .build();
        } else {
            throw new RuntimeException("Empleado no encontrado con id: " + empleadoRequestDTO.getEmpleadoId());
        }
    }


    @Override
    public EmpleadoResponseDTO ObtenerPorId(Long id) {
        return empleadoRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public EmpleadoResponseDTO eliminarEmpleado(Long id) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();

             if (empleado.getEstado() == null) {
                empleado.setEstado(false);
            } else {
                empleado.setEstado(false);
            }

             Empleado empleadoGuardado = empleadoRepository.save(empleado);

             return empleadoMapper.toDTO(empleadoGuardado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    private EmpleadoResponseDTO toDTO(Empleado empleado) {
        return EmpleadoResponseDTO.builder()
                .id(empleado.getId())
                .email(empleado.getEmail())
                .sueldo(empleado.getSueldo())
                .apellidoPaterno(empleado.getApellidoPaterno())
                .apellidoMaterno(empleado.getApellidoMaterno())
                .direccion(empleado.getDireccion())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .nombre(empleado.getNombre())
                .horarioId(empleado.getHorario().getId())
                .descripcionHorario(empleado.getHorario().getDescripcion())
                .departamentoId(empleado.getDepartamento().getId())
                .descripcionDepartamento(empleado.getDepartamento().getDescripcion())
                .estado(true)
                .build();
    }
}
