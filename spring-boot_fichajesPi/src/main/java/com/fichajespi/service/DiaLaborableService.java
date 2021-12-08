package com.fichajespi.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.entity.Calendario;
import com.fichajespi.entity.DiaLaborable;
import com.fichajespi.repository.DiaLaborableRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class DiaLaborableService
		extends CommonServiceImpl<DiaLaborable, DiaLaborableRepository> {

	public boolean existsByDia(Date dia) {
		return repository.existsByDia(dia);
	}

//	public Optional<DiaLaborable> getByDiaInActiveCalendar(Date dia, Calendario calendario) {
	public Optional<DiaLaborable> getByDiaInActiveCalendar(LocalDate dia,
			Calendario calendario) {
		return repository.getByDiaActive(dia, calendario);
	}

	public List<DiaLaborable> saveAll(List<DiaLaborable> lista) {
		return repository.saveAll(lista);
	}

}
