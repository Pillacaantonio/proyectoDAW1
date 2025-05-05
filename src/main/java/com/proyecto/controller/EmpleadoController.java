package com.proyecto.controller;

import com.proyecto.dto.empleado.EmpleadoRequestDTO;
import com.proyecto.dto.empleado.EmpleadoResponseDTO;
import com.proyecto.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    @GetMapping
    public List<EmpleadoResponseDTO> listar(){
        return empleadoService.listarEmpleados();
    }
    @PostMapping
    public EmpleadoResponseDTO guardar(@RequestBody EmpleadoRequestDTO dto){
        return empleadoService.crearEmpleado(dto);
    }
    @GetMapping("/{id}")
    public EmpleadoResponseDTO buscar(@PathVariable Long id){
        return empleadoService.ObtenerPorId(  id);
    }
    @PutMapping("/{empleadoId}")
    public ResponseEntity<EmpleadoResponseDTO> actualizarEmpleado(@PathVariable Long empleadoId,  @RequestBody EmpleadoRequestDTO empleadoRequestDTO) {
         empleadoRequestDTO.setEmpleadoId(empleadoId);

         EmpleadoResponseDTO empleadoResponseDTO = empleadoService.actualizarEmpleado(empleadoRequestDTO);

         return ResponseEntity.ok(empleadoResponseDTO);
    }

    @PatchMapping("/{id}")
    public EmpleadoResponseDTO eliminar(@PathVariable Long id) {
        return empleadoService.eliminarEmpleado(id);
    }

}
