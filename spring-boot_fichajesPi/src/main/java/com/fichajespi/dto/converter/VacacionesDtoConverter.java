package com.fichajespi.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.VacacionesDto;
import com.fichajespi.entity.Usuario;
import com.fichajespi.entity.Vacaciones;
import com.fichajespi.service.UsuarioService;

@Component
public class VacacionesDtoConverter {

	@Autowired
	private UsuarioService service;

	public Vacaciones transform(VacacionesDto dto) {
		Vacaciones vacaciones = new Vacaciones();

		// comprobamos que la fecha fin es posterior o igual a la de inicio
		int comparacion = dto.getInicio().compareTo(dto.getFin());
		if (comparacion > 0) {
			return null;
		}

		vacaciones.setInicio(dto.getInicio());
		vacaciones.setFin(dto.getFin());
		Usuario usuario = service.findByNumero(dto.getNumeroUsuario())
				.orElse(null);
		vacaciones.setUsuario(usuario);
		vacaciones.setConsumidas(false);
		return vacaciones;
	}

}
