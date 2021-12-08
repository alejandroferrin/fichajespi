package com.fichajespi.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Fichaje;
import com.fichajespi.entity.Usuario;
import com.fichajespi.repository.FichajeRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class FichajeService
		extends CommonServiceImpl<Fichaje, FichajeRepository> {

	public List<Fichaje> findByUsuarioAndDia(Usuario usuario, LocalDate dia) {
		return repository.findByUsuarioAndDiaOrderByHora(usuario, dia);
	}

}
