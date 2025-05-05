package com.proyecto.service;

import com.proyecto.dto.horario.HorarioRequestDTO;
import com.proyecto.dto.horario.HorarioResponseDTO;
import com.proyecto.entity.Horario;

import java.util.List;

public interface HorarioService {
    List<HorarioResponseDTO> listar();
    HorarioResponseDTO guardar(HorarioRequestDTO horario);
    HorarioResponseDTO actualizarHorario(HorarioRequestDTO horario);
    HorarioResponseDTO obtenerPorId(Long id);
    HorarioResponseDTO eliminar(Long id);

}
