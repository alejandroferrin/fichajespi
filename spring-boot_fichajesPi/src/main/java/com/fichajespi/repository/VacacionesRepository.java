package com.fichajespi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fichajespi.entity.Usuario;
import com.fichajespi.entity.Vacaciones;

@Repository
public interface VacacionesRepository extends JpaRepository<Vacaciones, Long>,
		JpaSpecificationExecutor<Vacaciones> {

	List<Vacaciones> findByUsuarioAndConsumidasFalseAndAprobadoTrue(
			Usuario usuario);

	List<Vacaciones> findByUsuarioId(Long id);
}
