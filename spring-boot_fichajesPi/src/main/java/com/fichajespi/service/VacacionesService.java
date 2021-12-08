package com.fichajespi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Usuario;
import com.fichajespi.entity.Vacaciones;
import com.fichajespi.repository.VacacionesRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class VacacionesService
		extends CommonServiceImpl<Vacaciones, VacacionesRepository>{

	public List<Vacaciones> findByUsuarioSinAgotar(Usuario usuario) {
		return repository.findByUsuarioAndConsumidasFalseAndAprobadoTrue(usuario);
	}

	public List<Vacaciones> findByUser(Long id) {
		return repository.findByUsuarioId(id);
	}
}
