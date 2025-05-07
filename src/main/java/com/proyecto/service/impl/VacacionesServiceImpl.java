package com.proyecto.service.impl;

import com.proyecto.dto.vacaciones.VacacionesRequestDTO;
import com.proyecto.dto.vacaciones.VacacionesResponseDTO;
import com.proyecto.entity.Empleado;
import com.proyecto.entity.Vacaciones;
import com.proyecto.mapper.VacacionesMapper;
import com.proyecto.repository.EmpleadoRepository;
import com.proyecto.repository.VacacionesRepository;
import com.proyecto.service.VacacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VacacionesServiceImpl implements VacacionesService {

    private final VacacionesRepository solicitudVacacionesRepository;
    private final VacacionesMapper solicitudVacacionesMapper;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<VacacionesResponseDTO> listar() {
        return solicitudVacacionesRepository.findAll().stream()
                .map(solicitudVacacionesMapper::toDTO)
                .toList();
    }

    @Override
    public VacacionesResponseDTO crear(VacacionesRequestDTO dto) {
        Vacaciones solicitud = solicitudVacacionesMapper.toEntity(dto);

        // Asignar empleado desde el ID
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(dto.getEmpleadoId());
        empleadoOpt.ifPresent(solicitud::setEmpleado);
        Vacaciones guardada = solicitudVacacionesRepository.save(solicitud);
        return solicitudVacacionesMapper.toDTO(guardada);
    }

    @Override
    public VacacionesResponseDTO actualizar(VacacionesRequestDTO dto) {
        Optional<Vacaciones> solicitudOpt = solicitudVacacionesRepository.findById(dto.getEmpleadoId());
        if (solicitudOpt.isPresent()) {
            Vacaciones solicitud = solicitudOpt.get();
            solicitud.setFechaInicio(dto.getFechaInicio());
            solicitud.setFechaFin(dto.getFechaFin());
            solicitud.setMotivo(dto.getMotivo());
            Vacaciones actualizada = solicitudVacacionesRepository.save(solicitud);
            return solicitudVacacionesMapper.toDTO(actualizada);
        } else {
            throw new RuntimeException(" no encontrada con id: " + dto.getEmpleadoId());
        }
    }

    @Override
    public VacacionesResponseDTO obtenerPorId(Long id) {
        return solicitudVacacionesRepository.findById(id)
                .map(solicitudVacacionesMapper::toDTO)
                .orElse(null);
    }

    @Override
    public VacacionesResponseDTO eliminar(Long id) {
        Optional<Vacaciones> solicitudOpt = solicitudVacacionesRepository.findById(id);
        if (solicitudOpt.isPresent()) {
            Vacaciones solicitud = solicitudOpt.get();
            solicitudVacacionesRepository.delete(solicitud);
            return solicitudVacacionesMapper.toDTO(solicitud);
        } else {
            throw new RuntimeException(" no encontrada con id: " + id);
        }
    }
}
