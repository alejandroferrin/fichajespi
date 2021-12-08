package com.fichajespi.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Calendario;
import com.fichajespi.repository.CalendarioRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class CalendarioService
		extends CommonServiceImpl<Calendario, CalendarioRepository> {

	public Optional<Calendario> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	public Optional<Calendario> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<Calendario> getActive() {
//		return repository.getActive();
		LocalDate ld = LocalDate.now();
		list().stream().forEach(c -> {
			if (c.getYear() == ld.getYear()) {
				c.setActive(true);
				save(c);
			} else {
				c.setActive(false);
				save(c);
			}
		});
//		return repository.getActive();
		return repository.findByActiveTrue();
	}

}
