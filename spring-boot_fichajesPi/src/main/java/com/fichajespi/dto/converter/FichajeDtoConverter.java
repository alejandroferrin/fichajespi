package com.fichajespi.dto.converter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.FichajeDto;
import com.fichajespi.dto.entity.FichajeDtoReqRes;
import com.fichajespi.entity.Fichaje;
import com.fichajespi.entity.Usuario;
import com.fichajespi.entity.enums.TipoFichaje;
import com.fichajespi.service.FichajeService;
import com.fichajespi.service.UsuarioService;

@Component
public class FichajeDtoConverter {

	@Autowired
	private UsuarioService service;
	@Autowired
	private FichajeService fichajeService;

	public FichajeDto inverseTransform(Fichaje f) {

		return null;

	}

	public Fichaje transform(FichajeDtoReqRes dto) {
		Fichaje f = new Fichaje();
		f.setHora(dto.getHora());
		f.setDia(dto.getDia());
		Usuario usuario = service
				.findByNumero(dto.getNumeroUsuario())
				.orElse(null);
		f.setUsuario(usuario);
		f.setTipo(dto.getTipo());
		f.setOrigen(dto.getOrigen());

		return f;
	}

	public FichajeDtoReqRes fichar(FichajeDtoReqRes fichajeDto) {

		fichajeDto.setHora(LocalTime.now());
		fichajeDto.setDia(LocalDate.now());

		Fichaje fichaje = transform(fichajeDto);

		Usuario usuario = fichaje.getUsuario();
		if (usuario != null) {
			boolean workingState = usuario.getWorking();
			if (workingState)
				fichaje.setTipo(TipoFichaje.SALIDA.toString());
//				fichaje.setTipo("SALIDA");
			else
				fichaje.setTipo(TipoFichaje.ENTRADA.toString());
//				fichaje.setTipo("ENTRADA");
			usuario.setWorking(!workingState);
			StringBuilder sb = new StringBuilder();
			sb.append(fichaje.getDia());
			sb.append(" ");
			DateTimeFormatter myTime = DateTimeFormatter.ofPattern("HH:mm:ss");
			sb.append(myTime.format(fichaje.getHora()));
			sb.append(" - ");
			sb.append(fichaje.getTipo());
			usuario.setUltimoFichaje(sb.toString());
			service.save(usuario);
			fichaje.setUsuario(usuario);
			fichajeService.save(fichaje);
			fichajeDto
					.setNombreUsuario(fichaje.getUsuario().getNombreEmpleado());
			fichajeDto.setHora(fichaje.getHora());
			fichajeDto.setTipo(
					fichaje.getUsuario().getWorking() ? "entrada" : "salida");

			return fichajeDto;
		}
		return null;

	}

}
