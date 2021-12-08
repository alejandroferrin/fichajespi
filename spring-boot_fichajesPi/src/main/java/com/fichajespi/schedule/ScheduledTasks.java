package com.fichajespi.schedule;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fichajespi.dto.converter.FichajeDtoConverter;
import com.fichajespi.dto.entity.FichajeDto;
import com.fichajespi.dto.entity.FichajeDtoReqRes;
import com.fichajespi.entity.Calendario;
import com.fichajespi.entity.DiaLaborable;
import com.fichajespi.entity.Fichaje;
import com.fichajespi.entity.Permiso;
import com.fichajespi.entity.Usuario;
import com.fichajespi.entity.Vacaciones;
import com.fichajespi.events.EventSender;
import com.fichajespi.events.Incidencias;
import com.fichajespi.logger.LoggerService;
import com.fichajespi.service.CalendarioService;
import com.fichajespi.service.DiaLaborableService;
import com.fichajespi.service.FichajeService;
import com.fichajespi.service.PermisoService;
import com.fichajespi.service.UsuarioService;
import com.fichajespi.service.VacacionesService;

@Component
public class ScheduledTasks {
	@Autowired
	CalendarioService calendarioService;
	@Autowired
	DiaLaborableService diaService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	FichajeService fichajeService;
	@Autowired
	EventSender incidenciaSender;
	@Autowired
	FichajeDtoConverter fichajeDtoToFichaje;
	@Autowired
	VacacionesService vacacionesService;
	@Autowired
	PermisoService permisoService;
	@Autowired
	LoggerService logger;

	// second-minute-hour-day_of_month-month-day(s)_of_week
	@Scheduled(cron = "0 1 0 * * ?") // todos los dias a las 00:01
//	@Scheduled(cron = "@daily")  
	public void analizarFichajes() {

		logger.logInfo("Ejecutando análisis de fichajes");

		// Obtenemos el calendario activo
		Calendario calendarioActivo = calendarioService.getActive().orElse(null);

		// Obtenemos el día de hoy
		LocalDate hoy = LocalDate.now().minusDays(1);

		// Intentamos conseguir el día laborable correspondiente a hoy, si no
		// existe -> null
		DiaLaborable diaLaborable = diaService.getByDiaInActiveCalendar(hoy, calendarioActivo).orElse(null);

		// Si el día Laborable existe empezamos las comprobaciones de los
		// fichajes de la
		// plantilla
		if (diaLaborable != null) {

			// Obtenemos una lista de usuarios que no están de baja o de
			// vacaciones
			List<Usuario> usuarios = usuarioService.list();
			usuarios.stream().forEach(u -> {

				// no comprobamos los fichajes de admin
				if (!u.getAdmin()) {

					// Obtenemos los periodos de vacaciones aprobados y sin agotar
					List<Vacaciones> vacaciones = vacacionesService.findByUsuarioSinAgotar(u);

					// Comprobamos si el usuario tiene un periodo de vacaciones
					// vigente y lo marcamos como de vacaciones
					// Si se ha agotado sus vacaciones volvemos a marcar las
					// vacaciones como false
					managePeriodoVacaciones(vacaciones, u, diaLaborable);

					// comprobamos si el usuario está trabajando o no
					if (u.getEnVacaciones()) { // Usuario de vacaciones
						// Descontar día de vacaciones
						u.setDiasVacaciones(u.getDiasVacaciones() - 1);
						usuarioService.save(u);
					} else if (u.getDeBaja()) { // Usuario de baja

						// De baja no se comprueban los fichajes

					} else { // Usuario no de vacaciones ni de baja

						// obtenemos los fichajes solo los fichajes de
						// hoy+++++++++++++++++++++++++++++++++++++++
						List<Fichaje> fichajes = fichajeService.findByUsuarioAndDia(u, diaLaborable.getDia());

						if (fichajes.size() == 0) { // No se han realizado fichajes
							generaIncidencia(diaLaborable, u, Incidencias.AUSENCIA);
						} else if (fichajes.size() % 2 != 0) { // impar
							// Cerramos el fichaje abierto
							FichajeDtoReqRes fichaje = new FichajeDtoReqRes();
							fichaje.setNumeroUsuario(u.getNumero());

							/////////////////////////////////////////////////////
							// comentar para realizar pruebas y que el fichaje que guarde no repita key con
							///////////////////////////////////////////////////// los generados con mockaroo
							fichajeDtoToFichaje.fichar(fichaje);
							/////////////////////////////////////////////////////

							generaIncidencia(diaLaborable, u, Incidencias.FICHAJE_IMPAR);

						} else if (fichajes.size() % 2 == 0) { // par

							// ###############_Calculamos el número de minutos trabajadas
							long minTrabajados = calculateMinutosTrabajados(fichajes);

							// Obtenemos el número de horas teóricas del día para
							// comparar
							LocalTime horaInicio = diaLaborable.getHoraInicio();
							LocalTime horaFin = diaLaborable.getHoraFin();
							Duration duration = Duration.between(horaInicio, horaFin);
							long minTeoricos = duration.toMinutes();

							// Comprobamos si ha trabajado suficientes horas con una
							// entrada y una salida
							if (fichajes.size() == 2) {

								compruebaHoras(minTrabajados, minTeoricos, diaLaborable, u);

							} else if (fichajes.size() > 2) { // mas de dos fichajes
								long minutosPermiso = 0;

								// Obtenemos los permisos para el día de hoy
								// aprobados
								List<Permiso> permisos = permisoService.findByUsuarioAprobado(u);

								// Si no hemos obtenido ningún permiso lanzamos una
								// incidencia para evidenciar que se han producido
								// salidas no autorizadas
								if (permisos.size() == 0) {
									generaIncidencia(diaLaborable, u, Incidencias.AUSENCIA_NO_AUTORIZADA);
								}

								// Recorremos los permisos de hoy y sumamos su
								// duración
								minutosPermiso += sumaMinutosPermisos(permisos, diaLaborable);

								// restamos los permisos concedidos a los minutos
								// teóricos
								minTeoricos -= minutosPermiso;

								// comprobamos si se ha trabajado el tiempo
								// suficiente
								compruebaHoras(minTrabajados, minTeoricos, diaLaborable, u);

							} // ##################_fin comprobacion horas trabajadas

							// Obtenemos las horas de entrada límite para comprobar
							// si se ha llegado al trabajo a la hora correcta

							LocalTime entradaTeorica = diaLaborable.getHoraInicio();

							LocalTime limiteInfEntrada = entradaTeorica
									.plusMinutes(-calendarioActivo.getMinutosMenosEntrada());

							LocalTime limiteSupEntrada = entradaTeorica
									.plusMinutes(calendarioActivo.getMinutosMasEntrada());

							int comparacionInf = fichajes.get(0).getHora().compareTo(limiteInfEntrada);
							int comparacionSup = fichajes.get(0).getHora().compareTo(limiteSupEntrada);

							if (comparacionInf < 0 || comparacionSup > 0) {
								generaIncidencia(diaLaborable, u, Incidencias.NO_ENTRA_A_TIEMPO);

							} // Fin of fichajes pares
						}
					}
				}
			});// Fin of foreach usuarios
		} else

		{
			System.out.println("diaLaborable null");
		}

	}

	private void generaIncidencia(DiaLaborable dia, Usuario u, String incidencia) {

		StringBuilder sb = new StringBuilder();
		sb.append("El día: " + dia.getDia());
		sb.append("\n\nEl usuario " + u.getNombreEmpleado());
		sb.append(" con el número " + u.getNumero());
		sb.append(" ha generado la siguiente incidencia");
		sb.append(":\n\n");
		sb.append(incidencia);
		incidenciaSender.createEvent("Incidencia", sb.toString(), dia.getDia(), u, incidencia);
		System.out.println("Email enviado");
	}

	private long calculateMinutosTrabajados(List<Fichaje> fichajes) {

		long minTrabajados = 0;
		for (int i = 0; i < fichajes.size(); i++) {
			// Calculamos las horas transcurridas entre parejas
			if (i % 2 == 0) {
				LocalTime entrada = fichajes.get(i).getHora();
				LocalTime salida = fichajes.get(i + 1).getHora();
				Duration duration = Duration.between(entrada, salida);
				minTrabajados += duration.toMinutes();
			}
		}
		return minTrabajados;
	}

	private void compruebaHoras(long minTrabajados, long minTeoricos, DiaLaborable diaLaborable, Usuario u) {

		if (minTrabajados < minTeoricos) {
			generaIncidencia(diaLaborable, u, Incidencias.MENOS_HORAS);
		}
	}

	private void managePeriodoVacaciones(List<Vacaciones> vacaciones, Usuario u, DiaLaborable diaLaborable) {

		vacaciones.stream().forEach(v -> {
			int compareIni = diaLaborable.getDia().compareTo(v.getInicio());
			int compareFin = diaLaborable.getDia().compareTo(v.getFin());

			if (compareIni >= 0 && compareFin <= 0) {
				// el usuario está de vacaciones
				u.setEnVacaciones(true);
				usuarioService.save(u);
			} else if (compareFin > 0) {
				// las vacaciones se pasaron y las marcamos como
				// agotadas
				v.setConsumidas(true);
				vacacionesService.save(v);
				u.setEnVacaciones(false);
				usuarioService.save(u);
			}
		});
	}

	private long sumaMinutosPermisos(List<Permiso> permisos, DiaLaborable diaLaborable) {

		long minutosPermiso = 0;
		for (Permiso p : permisos) {

			LocalDate diaPermiso = p.getDia();
			int compareDiaPermiso = diaPermiso.compareTo(diaLaborable.getDia());
			if (compareDiaPermiso == 0) {
				Duration permisoDuration = Duration.between(p.getHoraInicio(), p.getHoraFin());
				minutosPermiso += permisoDuration.toMinutes();
			}
		}
		return minutosPermiso;

	}

}
