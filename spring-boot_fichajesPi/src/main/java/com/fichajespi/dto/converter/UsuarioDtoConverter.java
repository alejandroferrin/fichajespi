package com.fichajespi.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.entity.UsuarioDto;
import com.fichajespi.dto.entity.UsuarioDtoEdit;
import com.fichajespi.dto.entity.UsuarioDtoEditPassword;
import com.fichajespi.entity.Rol;
import com.fichajespi.entity.Usuario;
import com.fichajespi.mail.EmailService;
import com.fichajespi.security.enums.RolNombre;
import com.fichajespi.service.RolService;

@Component
public class UsuarioDtoConverter {

	@Autowired
	private RolService rolService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmailService emailService;

	public Usuario transformNewAdmin(UsuarioDto dto) {

		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
		usuario.setNumero(dto.getNumero());
		usuario.setNombreEmpleado(dto.getNombreEmpleado());
		usuario.setDni(dto.getDni());
		usuario.setDiasVacaciones(0);
		usuario.setHorasGeneradas(0.0);
		usuario.setEnVacaciones(false);
		usuario.setDeBaja(false);
		usuario.setWorking(false);
		usuario.setAdmin(true);

		List<Rol> roles = new ArrayList<>();
		roles.add(rolService.findByRolNombre(RolNombre.ROLE_USER).get());
		if (dto.getRoles() != null) {
			if (dto.getRoles().contains("rrhh"))
				roles.add(rolService.findByRolNombre(RolNombre.ROLE_RRHH).get());
		}
		usuario.setRoles(roles);

		return usuario;

	}

	public Usuario transformNew(UsuarioDto dto) {

		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		String password;
		boolean isAdmin = dto.getNumero().equals("fichajesPi_1234");
		if (isAdmin) {
			password = dto.getPassword();
		} else {
			password = generatePassword();
		}
		usuario.setPassword(passwordEncoder.encode(password));
		usuario.setNumero(dto.getNumero());
		usuario.setNombreEmpleado(dto.getNombreEmpleado());
		usuario.setDni(dto.getDni());
		usuario.setDiasVacaciones(0);
		usuario.setHorasGeneradas(0.0);
		usuario.setEnVacaciones(false);
		usuario.setDeBaja(false);
		usuario.setWorking(false);
		usuario.setAdmin(false);

//		Set<Rol> roles = new HashSet<>();
		List<Rol> roles = new ArrayList<>();
		roles.add(rolService.findByRolNombre(RolNombre.ROLE_USER).get());
		if (dto.getRoles() != null) {
			if (dto.getRoles().contains("rrhh"))
				roles.add(rolService.findByRolNombre(RolNombre.ROLE_RRHH).get());
//			if (dto.getRoles().contains("admin"))
//				roles.add(rolService.findByRolNombre(RolNombre.ROLE_ADMIN).get());
		}
		usuario.setRoles(roles);

		StringBuilder body = new StringBuilder("Sus credenciales de acceso a FichajesPi son:\n\n");
		body.append(String.format("Número de empleado: %s \n", usuario.getNumero()));
		body.append(String.format("Contraseña: %s \n\n", password));
		body.append("Puede cambiar su contraseña desde la aplicación.");

		if (!isAdmin)
			emailService.sendEmail(
					usuario.getEmail(),
					"Usuario creado en FichajesPi",
					body.toString());

		return usuario;

	}

	public UsuarioDto inverseTransform(Usuario u) {

		return UsuarioDto
				.builder()
				.id(u.getId())
				.email(u.getEmail())
				.numero(u.getNumero())
				.nombreEmpleado(u.getNombreEmpleado())
				.dni(u.getDni())
				.diasVacaciones(u.getDiasVacaciones())
				.horasGeneradas(u.getHorasGeneradas())
				.working(u.getWorking())
				.enVacaciones(u.getEnVacaciones())
				.deBaja(u.getDeBaja())
				.build();

	}

	public Usuario transformEdit(Usuario u, UsuarioDtoEdit editar) {
		u.setEmail(editar.getEmail());
//		u.setPassword(passwordEncoder.encode(editar.getPassword()));
		u.setNumero(editar.getNumero());
		u.setNombreEmpleado(editar.getNombreEmpleado());
		u.setDni(editar.getDni());
		u.setDiasVacaciones(editar.getDiasVacaciones());
		u.setHorasGeneradas(editar.getHorasGeneradas());
		u.setEnVacaciones(editar.getEnVacaciones());
		u.setDeBaja(editar.getDeBaja());
		u.setWorking(editar.getWorking());
		return u;
	}

	public Usuario transformEditPassword(Usuario u, UsuarioDtoEditPassword editar) {
		u.setPassword(passwordEncoder.encode(editar.getPassword()));
		return u;
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

}
