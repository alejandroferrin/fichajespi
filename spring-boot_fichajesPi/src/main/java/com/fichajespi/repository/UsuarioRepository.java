package com.fichajespi.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fichajespi.entity.Rol;
import com.fichajespi.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>,
		JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByNumero(String numero);

	Optional<Usuario> findByDni(String dni);

	boolean existsByNumero(String numero);

	boolean existsByEmail(String email);

	boolean existsByDni(String dni);

//	@Query("SELECT u FROM Usuario u WHERE u.enVacaciones = FALSE AND u.deBaja = FALSE")
//	List<Usuario> getUsersWorking();

	List<Usuario> findByEnVacacionesTrueAndDeBajaFalse();

	List<Usuario> findByRolesIn(Set<Rol> roles);


}
