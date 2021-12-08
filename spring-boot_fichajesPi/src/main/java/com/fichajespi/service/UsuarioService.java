package com.fichajespi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Rol;
import com.fichajespi.entity.Usuario;
import com.fichajespi.repository.UsuarioRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class UsuarioService extends CommonServiceImpl<Usuario, UsuarioRepository> {

//	public Optional<Usuario> findByEmail(String email) {
//		return repository.findByEmail(email);
//	}

	public Optional<Usuario> findByNumero(String numero) {
		return repository.findByNumero(numero);
	}

//	public Optional<Usuario> findByDni(String dni) {
//		return repository.findByDni(dni);
//	}

	public List<Usuario> getUsersWorking() {
//		return repository.getUsersWorking();
		return repository.findByEnVacacionesTrueAndDeBajaFalse();
	}

	public List<Usuario> findByRoles(Set<Rol> roles) {
		return repository.findByRolesIn(roles);
	}

	public boolean existsByNumero(String numero) {
		return repository.existsByNumero(numero);
	}

	public boolean existsByDni(String dni) {
		return repository.existsByDni(dni);
	}

	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

}
