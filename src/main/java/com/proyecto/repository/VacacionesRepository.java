package com.proyecto.repository;

import com.proyecto.entity.Empleado;
import com.proyecto.entity.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {
    List<Vacaciones> findByEmpleado(Empleado empleado);
}
