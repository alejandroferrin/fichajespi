package com.fichajespi.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.DiaDto;
import com.fichajespi.entity.Calendario;
import com.fichajespi.entity.DiaLaborable;
import com.fichajespi.service.CalendarioService;

@Component
public class DiaDtoConverter {

	@Autowired
	private CalendarioService service;

	public DiaLaborable transform(DiaDto dto) {
		DiaLaborable dia = new DiaLaborable();
		dia.setDia(dto.getDia());
		dia.setHoraInicio(dto.getHoraInicio());
		dia.setHoraFin(dto.getHoraFin());
		Calendario calendario = service
				.findByNombre(dto.getCalendarioNombre())
				.orElse(null);
		dia.setCalendario(calendario);
		return dia;
	}

	public DiaLaborable transformById(DiaDto dto, Long idCalendario) {
		DiaLaborable dia = new DiaLaborable();
		dia.setDia(dto.getDia());
		dia.setHoraInicio(dto.getHoraInicio());
		dia.setHoraFin(dto.getHoraFin());
		Calendario calendario = service
				.findById(idCalendario)
				.orElse(null);
		dia.setCalendario(calendario);
		return dia;
	}

}
