package com.fichajespi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fichajespi.entity.Rol;
import com.fichajespi.security.enums.RolNombre;

@Repository
public interface RolRepository
		extends JpaRepository<Rol, Long>, JpaSpecificationExecutor<Rol> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
