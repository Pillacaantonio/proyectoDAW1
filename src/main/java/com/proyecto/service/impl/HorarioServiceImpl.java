package com.proyecto.service.impl;

import com.proyecto.dto.horario.HorarioRequestDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.entity.Empleado;
import com.proyecto.entity.Horario;
import com.proyecto.mapper.HorarioMapper;
import com.proyecto.repository.HorarioRepository;
import com.proyecto.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HorarioServiceImpl  implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorarioMapper horarioMapper;
    @Override
    public List<HorarioResponseDTO> listar() {
        return  horarioRepository.findAll().stream()
                .map(horarioMapper::toDTO)
                .toList();
    }

    @Override
    public HorarioResponseDTO guardar(HorarioRequestDTO dto) {
        Horario horario= horarioMapper.toEntity(dto);
        horario.setEstado(true);

        Horario guardado = horarioRepository.save(horario);
        return horarioMapper.toDTO(guardado);
    }

    @Override
    public HorarioResponseDTO actualizarHorario(HorarioRequestDTO horarioRequestDTO) {
         Optional<Horario> horarioOptional = horarioRepository.findById(horarioRequestDTO.getHorarioId());
         if (horarioOptional.isPresent()) {
             Horario horario = horarioOptional.get();
             horario.setDescripcion(horarioRequestDTO.getDescripcion());
             horarioRepository.save(horario);
            return HorarioResponseDTO.builder()
                    .id(horario.getId())
                    .descripcion(horario.getDescripcion())
                    .horas_asignadas(horario.getHoras_asignadas())
                    .estado(horario.getEstado())
                    .build();
        } else {
            throw new RuntimeException("Horario no encontrado con id: " + horarioRequestDTO.getHorarioId());
        }
    }
    @Override
    public HorarioResponseDTO obtenerPorId(Long id) {
        return horarioRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public HorarioResponseDTO eliminar(Long id) {
    Optional<Horario> horarioOptional = horarioRepository.findById(id);
    if(horarioOptional.isPresent()) {
        Horario horario = horarioOptional.get();
        if(horario.getEstado() == null) {
            horario.setEstado(false);

        }else {
            horario.setEstado(false);
        }
        Horario horarioGuardar = horarioRepository.save(horario);
        return horarioMapper.toDTO(horarioGuardar);

    }else {
        throw new RuntimeException("Horario no encontrado");

    }
    }

    private HorarioResponseDTO toDTO(Horario horario) {

        return HorarioResponseDTO.builder()
                .id(horario.getId())
                .descripcion(horario.getDescripcion())
                .horas_asignadas(horario.getHoras_asignadas())
                .estado(horario.getEstado())
                .build();
    }


}
