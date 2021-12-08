package com.fichajespi.dto.converter;

import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.CalendarioDto;
import com.fichajespi.entity.Calendario;

@Component
public class CalendarioDtoConverter {

//	@Autowired
//	private DiaDtoToDia service;

	public Calendario transform(CalendarioDto dto) {
		Calendario c = new Calendario();
		c.setNombre(dto.getNombre());
		c.setMinutosMasEntrada(dto.getMinutosMasEntrada());
		c.setMinutosMenosEntrada(dto.getMinutosMenosEntrada());
		c.setYear(dto.getYear());
//		c.setDias(
//				dto.getDias().stream()
//						.map(diaDto -> service.transform(diaDto))
//						.collect(Collectors.toList()));
		return c;
	}

	public CalendarioDto inverseTransform(Calendario c) {
		CalendarioDto dto = new CalendarioDto();
		dto.setId(c.getId());
		dto.setNombre(c.getNombre());
		dto.setYear(c.getYear());
		dto.setMinutosMasEntrada(c.getMinutosMasEntrada());
		dto.setMinutosMenosEntrada(c.getMinutosMenosEntrada());
		return dto;
	}

}
