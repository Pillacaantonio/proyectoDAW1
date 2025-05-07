package com.proyecto.service.impl;

import com.proyecto.dto.empleado.EmpleadoRequestDTO;
import com.proyecto.dto.empleado.EmpleadoResponseDTO;
import com.proyecto.entity.*;
import com.proyecto.mapper.EmpleadoMapper;
import com.proyecto.repository.*;
import com.proyecto.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final VacacionesRepository vacacionesRepository;
    private final CatalogoDiasNoLaborablesRepository catalogoRepository;
    private final HorarioRepository horarioRepository;
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
        return toDTO(empleadoRepository.save(empleado));
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
            }

            Empleado empleadoGuardado = empleadoRepository.save(empleado);

            return empleadoMapper.toDTO(empleadoGuardado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    private EmpleadoResponseDTO toDTO(Empleado empleado) {
        List<Vacaciones> vacaciones = vacacionesRepository.findByEmpleado(empleado);
        List<CatalogoDiasNoLaborables> catalogo = catalogoRepository.findAll();
        Set<LocalDate> diasNoLaborables = catalogo.stream()
                .map(CatalogoDiasNoLaborables::getFecha)
                .collect(Collectors.toSet());

        int diasAcumulados = calcularDiasAcumulados(empleado.getFechaIngreso());
        int diasConsumidos = calcularDiasConsumidos(vacaciones, diasNoLaborables);
        int diasDisponibles = diasAcumulados - diasConsumidos;

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
                .diasAcumulados(diasAcumulados)
                .diasConsumidos(diasConsumidos)
                .diasDisponibles(diasDisponibles)
                .build();
    }

    //Vacaciones
    private int calcularDiasConsumidos(List<Vacaciones> vacacionesList, Set<LocalDate> diasNoLaborables) {
        int total = 0;

        for (Vacaciones vac : vacacionesList) {
            LocalDate actual = vac.getFechaInicio();
            LocalDate fin = vac.getFechaFin();

            while (!actual.isAfter(fin)) {
                if (!diasNoLaborables.contains(actual)) {
                    total++;
                }
                actual = actual.plusDays(1);
            }
        }

        return total;
    }


    private int calcularDiasAcumulados(LocalDate fechaAlta) {
        LocalDate hoy = LocalDate.now();
        int años = hoy.getYear() - fechaAlta.getYear();
        int meses = hoy.getMonthValue() - fechaAlta.getMonthValue();
        int totalMeses = (años * 12) + meses;
        double diasAcumulados = totalMeses * 2.5;//Esto es en caso de regimen general, si es mype hay que parametrizar por que seria 1.25
        return (int) Math.floor(diasAcumulados);
    }

}
