package com.fichajespi.mail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fichajespi.entity.Rol;
import com.fichajespi.entity.Usuario;
import com.fichajespi.security.enums.RolNombre;
import com.fichajespi.service.RolService;
import com.fichajespi.service.UsuarioService;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;

	@Async
	public void sendEmail(String[] to, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		msg.setFrom("noreply@fichajespi.es");
		javaMailSender.send(msg);
	}


	@Async
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		msg.setFrom("noreply@fichajespi.es");
		javaMailSender.send(msg);
	}

	public String[] getDestinatarios(Usuario usuario) {
		// Obtenemos el rol de rrhh
		Rol rol = rolService.findByRolNombre(RolNombre.ROLE_RRHH).get();
		// Lo añadimos a un hashset para realizar una búsqueda
		Set<Rol> roles = new HashSet<>();
		roles.add(rol);
		// Obtenemos los usuarios con rol de rrhh
		List<Usuario> usuariosRrhh = usuarioService.findByRoles(roles);
		// Obtenemos sus emails
		List<String> destinatariosList = usuariosRrhh.stream()
				.map(u -> u.getEmail()).collect(Collectors.toList());
		// Añadimos el email del usuario causante de la incidencia
		destinatariosList.add(usuario.getEmail());
		Object[] objArr = destinatariosList.toArray();
		return Arrays.copyOf(objArr,
				objArr.length,
				String[].class);
	}

	@Async
	public void sendNotification(String numeroUsuario, String subject,
			String body) {
		Usuario usuario = usuarioService.findByNumero(numeroUsuario)
				.orElse(null);

		String[] destinatarios = getDestinatarios(usuario);
		sendEmail(destinatarios, subject, body);
		System.out.println("Email enviado");

	}

	public String generateBodyForVacaciones(String nombre, String numero,
			String diaInicio, String diaFin, String estado) {
		StringBuilder sb = new StringBuilder();
		sb.append("El usuario " + nombre);
		sb.append(" con número " + numero);
		sb.append(
				" ha solicitado vacaciones en el siguiente periodo:\n\n");
		sb.append("Día inicio: " + diaInicio + "\n");
		sb.append("Día fin: " + diaFin + "\n\n");
		sb.append("El estado de esta petición es: " + estado);
		return sb.toString();

	}

	public String generateBodyForPermiso(String nombre, String numero,
			String desc, String dia, String inicio, String fin, String estado) {
		StringBuilder sb = new StringBuilder();
		sb.append("El usuario " + nombre);
		sb.append(" con número " + numero);
		sb.append(
				" ha solicitado un permiso para ausentarse con la siguiente descripción:\n\n");
		sb.append(desc + "\n\n");
		sb.append("Día: " + dia + "\n");
		sb.append("Hora inicio: " + inicio + "\n");
		sb.append("Hora fin: " + fin + "\n\n");
		sb.append("El estado de esta petición es: " + estado);
		return sb.toString();

	}
}
