package com.fichajespi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Rol;
import com.fichajespi.security.enums.RolNombre;
import com.fichajespi.repository.RolRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class RolService extends CommonServiceImpl<Rol, RolRepository> {

	public Optional<Rol> findByRolNombre(RolNombre rolNombre) {
		return repository.findByRolNombre(rolNombre);
	}

}
