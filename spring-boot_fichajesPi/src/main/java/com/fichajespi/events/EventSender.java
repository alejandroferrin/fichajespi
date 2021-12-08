package com.fichajespi.events;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fichajespi.entity.Incidencia;
import com.fichajespi.entity.Usuario;
import com.fichajespi.mail.EmailService;
import com.fichajespi.service.IncidenciaService;

@Component
public class EventSender {

	@Autowired
	private IncidenciaService incidenciaService;
	@Autowired
	private EmailService emailService;

	public void createEvent(String subject, String descripcion, LocalDate dia,
			Usuario usuario, String resumen) {

		String[] destinatarios = emailService.getDestinatarios(usuario);

		// Creamos la incidencia
		Incidencia incidencia = new Incidencia();
		incidencia.setDescripcion(descripcion);
		incidencia.setDia(dia);
		incidencia.setUsuario(usuario);
		incidencia.setResumen(resumen);
		incidencia.setResuelta(false);
		incidencia.setExplicacion("");
		incidenciaService.save(incidencia);
		emailService.sendEmail(destinatarios, subject, descripcion);
	}

}
