package com.proyecto.service;

import com.proyecto.dto.empleado.EmpleadoRequestDTO;
import com.proyecto.dto.empleado.EmpleadoResponseDTO;

import java.util.List;

public interface EmpleadoService {
    List<EmpleadoResponseDTO> listarEmpleados();
    EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO dto);
    EmpleadoResponseDTO actualizarEmpleado(EmpleadoRequestDTO empleado);
    EmpleadoResponseDTO ObtenerPorId(Long id);
    EmpleadoResponseDTO eliminarEmpleado(Long id);

}
