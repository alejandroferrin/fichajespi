package com.fichajespi.dto.converter;

import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.IncidenciaDtoEdit;
import com.fichajespi.entity.Incidencia;

@Component
public class IncidenciaDtoConverter {


	public Incidencia transformEdit(Incidencia i, IncidenciaDtoEdit editar) {
		i.setResuelta(editar.getResuelta());
		i.setExplicacion(editar.getExplicacion());
		return i;
	}

}
