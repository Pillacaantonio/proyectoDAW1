package com.proyecto.service.impl;

import com.proyecto.dto.departamento.DepartamentoRequestDTO;
import com.proyecto.dto.departamento.DepartamentoResponseDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.entity.Departamento;
import com.proyecto.entity.Horario;
import com.proyecto.mapper.DepartamentoMapper;
import com.proyecto.repository.DepartamentoRepository;
import com.proyecto.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoResponseDTO> listar() {
        return departamentoRepository.findAll().stream()
                .map(departamentoMapper::toDTO)
                .toList();
    }

    @Override
    public DepartamentoResponseDTO guardar(DepartamentoRequestDTO dto) {
        Departamento departamento = departamentoMapper.toEntity(dto);
        departamento.setEstado(true);

        Departamento Guardado = departamentoRepository.save(departamento);
        return departamentoMapper.toDTO(Guardado);
    }

    @Override
    public DepartamentoResponseDTO actualizar(DepartamentoRequestDTO dto) {
        Optional<Departamento> departamentoOptional = departamentoRepository.findById(dto.getDepartamentoId());
        if (departamentoOptional.isPresent()) {
            Departamento departamento = departamentoOptional.get();
            departamento.setDescripcion(dto.getDescripcion());

            departamentoRepository.save(departamento);

            return DepartamentoResponseDTO.builder()
                    .id(departamento.getId())
                    .descripcion(departamento.getDescripcion())
                    .estado(departamento.getEstado())
                    .build();
        } else {
            throw new RuntimeException("Departamento no encontrado con id: " + dto.getDepartamentoId());
        }
    }


    @Override
    public DepartamentoResponseDTO eliminar(Long id) {
         Optional<Departamento> departamentoOptional = departamentoRepository.findById(id);
         if(departamentoOptional.isPresent()) {
             Departamento departamento = departamentoOptional.get();
             if(departamento.getEstado() == null){
                 departamento.setEstado(false);
             }else {
                 departamento.setEstado(false);
             }
             Departamento departamentoguardado = departamentoRepository.save(departamento);
             return departamentoMapper.toDTO(departamentoguardado);
         }else {
             throw new RuntimeException("departamento no encontrado");

         }


    }

    @Override
    public DepartamentoResponseDTO buscarPorId(Long id) {
        return departamentoRepository.findById(id).map(this::toDTO).orElse(null);
    }

    private DepartamentoResponseDTO toDTO(Departamento departamento) {
        return DepartamentoResponseDTO.builder()
                .id(departamento.getId())
                .descripcion(departamento.getDescripcion())
                .estado(departamento.getEstado())
                .build();
    }
}
