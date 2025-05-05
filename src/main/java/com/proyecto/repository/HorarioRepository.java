package com.proyecto.repository;

import com.proyecto.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
