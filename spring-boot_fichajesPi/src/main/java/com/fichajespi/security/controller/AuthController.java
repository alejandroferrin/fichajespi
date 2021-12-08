package com.fichajespi.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fichajespi.dto.converter.UsuarioDtoConverter;
import com.fichajespi.dto.entity.Mensaje;
import com.fichajespi.dto.entity.UsuarioDto;
import com.fichajespi.entity.Usuario;
import com.fichajespi.security.dto.JwtDto;
import com.fichajespi.security.dto.LoginUsuario;
import com.fichajespi.security.jwt.JwtProvider;
import com.fichajespi.service.UsuarioService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins ="http://localhost:4200")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UsuarioDtoConverter dtoConverter;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(
			@Valid @RequestBody UsuarioDto nuevoUsuario,
			BindingResult bindingResult) {

		if (nuevoUsuario.getDni().isBlank() ||
				nuevoUsuario.getEmail().isBlank() ||
				nuevoUsuario.getNombreEmpleado().isBlank() ||
				nuevoUsuario.getNumero().isBlank()) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje("Los campos nombre, numero, email o dni no pueden estar en blanco"));
		}

		if (bindingResult.hasErrors())

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje("Campos mal puestos o email inválido."));

		if (usuarioService.existsByNumero(nuevoUsuario.getNumero()))

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje("Ya existe el número de empleado."));

		if (usuarioService.existsByDni(nuevoUsuario.getDni()))

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje("Ya existe el dni del empleado."));

		if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje("Email en uso."));

		Usuario usuario = dtoConverter.transformNew(nuevoUsuario);

		usuarioService.save(usuario);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new Mensaje("Usuario creado"));

	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(
			@Valid @RequestBody LoginUsuario loginUsuario,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("campos mal puestos"),
					HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginUsuario.getNumero(),
						loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
	}

}
