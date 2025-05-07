package com.proyecto.repository;

import com.proyecto.entity.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, String> {
}
