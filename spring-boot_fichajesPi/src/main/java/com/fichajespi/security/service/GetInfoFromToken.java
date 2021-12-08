package com.fichajespi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fichajespi.entity.Rol;
import com.fichajespi.entity.Usuario;
import com.fichajespi.security.entity.RrhhDto;
import com.fichajespi.security.enums.RolNombre;
import com.fichajespi.security.jwt.JwtProvider;
import com.fichajespi.service.UsuarioService;

@Service
public class GetInfoFromToken {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	JwtProvider jwtProvider;

	public RrhhDto rrhhInfo(String token) {

		token = token.replace("Bearer ", "");

		boolean response = true;
		String numeroUsuario = "";

		if (jwtProvider.validateToken(token)) {
			numeroUsuario = jwtProvider.getSubjectFromToken(token);
			Usuario usuario = usuarioService.findByNumero(numeroUsuario).orElse(null);
			if (usuario != null) {
				for (Rol rol : usuario.getRoles()) {
					if (rol.getRolNombre() == RolNombre.ROLE_RRHH)
						response = false;
				}
			}
		}

		return new RrhhDto(response, numeroUsuario);
	}
}
