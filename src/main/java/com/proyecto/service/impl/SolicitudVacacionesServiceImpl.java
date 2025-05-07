package com.proyecto.service.impl;

import com.proyecto.dto.vacaciones.SolicitudVacacionesRequestDTO;
import com.proyecto.dto.vacaciones.SolicitudVacacionesResponseDTO;
import com.proyecto.entity.Empleado;
import com.proyecto.entity.SolicitudVacaciones;
import com.proyecto.mapper.SolicitudVacacionesMapper;
import com.proyecto.repository.EmpleadoRepository;
import com.proyecto.repository.SolicitudVacacionesRepository;
import com.proyecto.service.SolicitudVacacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SolicitudVacacionesServiceImpl implements SolicitudVacacionesService {

    private final SolicitudVacacionesRepository solicitudVacacionesRepository;
    private final SolicitudVacacionesMapper solicitudVacacionesMapper;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<SolicitudVacacionesResponseDTO> listarSolicitudes() {
        return solicitudVacacionesRepository.findAll().stream()
                .map(solicitudVacacionesMapper::toDTO)
                .toList();
    }

    @Override
    public SolicitudVacacionesResponseDTO crearSolicitud(SolicitudVacacionesRequestDTO dto) {
        SolicitudVacaciones solicitud = solicitudVacacionesMapper.toEntity(dto);

        // Asignar empleado desde el ID
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(dto.getEmpleadoId());
        empleadoOpt.ifPresent(solicitud::setEmpleado);

        solicitud.setEstado(dto.getEstado());

        SolicitudVacaciones guardada = solicitudVacacionesRepository.save(solicitud);
        return solicitudVacacionesMapper.toDTO(guardada);
    }

    @Override
    public SolicitudVacacionesResponseDTO actualizarSolicitud(SolicitudVacacionesRequestDTO dto) {
        Optional<SolicitudVacaciones> solicitudOpt = solicitudVacacionesRepository.findById(dto.getEmpleadoId());
        if (solicitudOpt.isPresent()) {
            SolicitudVacaciones solicitud = solicitudOpt.get();
            solicitud.setFechaInicio(dto.getFechaInicio());
            solicitud.setFechaFin(dto.getFechaFin());
            solicitud.setMotivo(dto.getMotivo());
            solicitud.setEstado(dto.getEstado());

            SolicitudVacaciones actualizada = solicitudVacacionesRepository.save(solicitud);
            return solicitudVacacionesMapper.toDTO(actualizada);
        } else {
            throw new RuntimeException("Solicitud no encontrada con id: " + dto.getEmpleadoId());
        }
    }

    @Override
    public SolicitudVacacionesResponseDTO obtenerPorId(Long id) {
        return solicitudVacacionesRepository.findById(id)
                .map(solicitudVacacionesMapper::toDTO)
                .orElse(null);
    }

    @Override
    public SolicitudVacacionesResponseDTO eliminarSolicitud(Long id) {
        Optional<SolicitudVacaciones> solicitudOpt = solicitudVacacionesRepository.findById(id);
        if (solicitudOpt.isPresent()) {
            SolicitudVacaciones solicitud = solicitudOpt.get();
            solicitudVacacionesRepository.delete(solicitud);
            return solicitudVacacionesMapper.toDTO(solicitud);
        } else {
            throw new RuntimeException("Solicitud no encontrada con id: " + id);
        }
    }
}
