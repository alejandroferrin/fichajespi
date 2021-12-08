package com.fichajespi.dto.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.PermisoDto;
import com.fichajespi.entity.Permiso;
import com.fichajespi.entity.Usuario;
import com.fichajespi.service.UsuarioService;

@Component
public class PermisoDtoConverter {

	@Autowired
	private UsuarioService service;

	public Permiso transform(PermisoDto dto) {

		Permiso permiso = new Permiso();
		LocalDateTime inicio = LocalDateTime.of(
				dto.getDia(),
				dto.getHoraInicio());
		LocalDateTime fin = LocalDateTime.of(
				dto.getDia(),
				dto.getHoraFin());

		// comprobamos que la fecha fin es posterior o igual a la de inicio
		int comparacion = inicio.compareTo(fin);
		if (comparacion > 0) {
			return null;
			// TODO mejor throw exception??
		}

		permiso.setHoraInicio(dto.getHoraInicio());
		permiso.setHoraFin(dto.getHoraFin());
		permiso.setDia(dto.getDia());
		permiso.setDescripcion(dto.getDescripcion());

		Usuario usuario = service.findByNumero(dto.getNumeroUsuario())
				.orElse(null);
		permiso.setUsuario(usuario);
		return permiso;
	}

}
