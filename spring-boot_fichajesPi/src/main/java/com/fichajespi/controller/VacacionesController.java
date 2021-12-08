package com.fichajespi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fichajespi.controller.common.CommonController;
import com.fichajespi.dto.converter.VacacionesDtoConverter;
import com.fichajespi.dto.entity.Mensaje;
import com.fichajespi.dto.entity.VacacionesDto;
import com.fichajespi.dto.entity.VacacionesDtoFilter;
import com.fichajespi.entity.Vacaciones;
import com.fichajespi.entity.enums.EstadosPeticion;
import com.fichajespi.mail.EmailService;
import com.fichajespi.security.entity.RrhhDto;
import com.fichajespi.security.service.GetInfoFromToken;
import com.fichajespi.service.VacacionesService;
import com.fichajespi.specifications.VacacionesSpecifications;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vacaciones")
//@CrossOrigin(origins = "http://localhost:4200")
public class VacacionesController
		extends CommonController<Vacaciones, VacacionesService> {

	@Autowired
	VacacionesDtoConverter dtoConverter;
	@Autowired
	EmailService emailService;
	@Autowired
	VacacionesSpecifications specifications;
	@Autowired
	GetInfoFromToken getInfoFromToken;

	@PostMapping("/create")
	public ResponseEntity<?> newVacaciones(@RequestBody VacacionesDto dto) {

		emailService.sendNotification(dto.getNumeroUsuario(),
				"Petición de vacaciones",
				emailService.generateBodyForVacaciones(dto.getNombreUsuario(),
						dto.getNumeroUsuario(),
						dto.getInicio().toString(),
						dto.getFin().toString(),
						EstadosPeticion.PENDIENTE.toString()));

		Vacaciones vacaciones = dtoConverter.transform(dto);

		if (vacaciones != null) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(service.save(vacaciones));

		} else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Mensaje(
							"La fecha de fin debe ser posterior a la de inicio."));

		}

	}

	@PutMapping("/aprobar/{id}")
	public ResponseEntity<?> aprobar(@PathVariable Long id) {
		return service.findById(id).map(d -> {
			d.setAprobado(true);
			d.setEstado(EstadosPeticion.APROBADO.toString());

			emailService.sendNotification(d.getUsuario().getNumero(),
					"Petición de vacaciones",
					emailService.generateBodyForVacaciones(
							d.getUsuario().getNombreEmpleado(),
							d.getUsuario().getNumero(),
							d.getInicio().toString(),
							d.getFin().toString(),
							EstadosPeticion.APROBADO.toString()));

			return ResponseEntity.ok(service.save(d));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

	@PutMapping("/denegar/{id}")
	public ResponseEntity<?> denegar(@PathVariable Long id) {
		return service.findById(id).map(d -> {
			d.setAprobado(false);
			d.setEstado(EstadosPeticion.DENEGADO.toString());

			emailService.sendNotification(d.getUsuario().getNumero(),
					"Petición de vacaciones",
					emailService.generateBodyForVacaciones(
							d.getUsuario().getNombreEmpleado(),
							d.getUsuario().getNumero(),
							d.getInicio().toString(),
							d.getFin().toString(),
							EstadosPeticion.DENEGADO.toString()));

			return ResponseEntity.ok(service.save(d));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

//	@ApiOperation("Obtiene una lista de objetos dado un id de usuario")
//	@GetMapping("/usuario/{id}")
//	public ResponseEntity<?> getByUsuarioId(@PathVariable Long id) {
//		List<Vacaciones> result = service.findByUser(id);
//		if (result == null)
//			return ResponseEntity.notFound().build();
//		else
//			return ResponseEntity.ok(result);
//	}

	@ApiOperation("Obtiene una lista paginada y filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/pagesFiltered")
	public ResponseEntity<Page<Vacaciones>> pageDtoSpec(
			@RequestBody VacacionesDtoFilter dto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestHeader("authorization") String token) {

		RrhhDto tokenUser = getInfoFromToken.rrhhInfo(token);
		if (tokenUser.isRrhh()) {
			dto.setUsuarioNumero(tokenUser.getNumber());
		}

		Specification<Vacaciones> spec = Specification
				.where(dto.getUsuarioNombre() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getUsuarioNombre()))
				.and(dto.getUsuarioNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getUsuarioNumero()))
				.and(dto.getUsuarioDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getUsuarioDni()))
				.and(dto.getUsuarioEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getUsuarioEmail()))
				.and(dto.getConsumidas() == null ? null
						: specifications.areConsumidas(
								dto.getConsumidas()))
//				.and(dto.getAprobado() == null ? null
//						: specifications.areAprobadas(
//								dto.getAprobado()))
				.and(dto.getEstado() == null ? null
						: specifications.estadoContains(
								dto.getEstado()))
				.and(dto.getInicioDesde() == null ? null
						: specifications.inicioDesde(
								dto.getInicioDesde()))
				.and(dto.getInicioHasta() == null ? null
						: specifications.inicioHasta(
								dto.getInicioHasta()))
				.and(dto.getFinDesde() == null ? null
						: specifications.inicioDesde(
								dto.getFinDesde()))
				.and(dto.getFinHasta() == null ? null
						: specifications.inicioHasta(
								dto.getFinHasta()));

		Page<Vacaciones> entities = service.pagesAndSpec(
				spec,
				PageRequest.of(page, size, Sort.by(order)));

		if (!asc)
			entities = service.pagesAndSpec(
					spec,
					PageRequest.of(page, size, Sort.by(order).descending()));

//		Page<VacacionesDto> entitiesDto = entities
//				.map(usu -> dtoConverter.inverseTransform(usu));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);

	}

	@ApiOperation("Obtiene una lista filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/listFiltered")
	public ResponseEntity<List<Vacaciones>> filteredList(
			@RequestBody VacacionesDtoFilter dto,
			@RequestHeader("authorization") String token) {

		RrhhDto tokenUser = getInfoFromToken.rrhhInfo(token);
		if (tokenUser.isRrhh()) {
			dto.setUsuarioNumero(tokenUser.getNumber());
		}

		Specification<Vacaciones> spec = Specification
				.where(dto.getUsuarioNombre() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getUsuarioNombre()))
				.and(dto.getUsuarioNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getUsuarioNumero()))
				.and(dto.getUsuarioDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getUsuarioDni()))
				.and(dto.getUsuarioEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getUsuarioEmail()))
				.and(dto.getConsumidas() == null ? null
						: specifications.areConsumidas(
								dto.getConsumidas()))
				.and(dto.getAprobado() == null ? null
						: specifications.areAprobadas(
								dto.getAprobado()))
				.and(dto.getInicioDesde() == null ? null
						: specifications.inicioDesde(
								dto.getInicioDesde()))
				.and(dto.getInicioHasta() == null ? null
						: specifications.inicioHasta(
								dto.getInicioHasta()))
				.and(dto.getFinDesde() == null ? null
						: specifications.inicioDesde(
								dto.getFinDesde()))
				.and(dto.getFinHasta() == null ? null
						: specifications.inicioHasta(
								dto.getFinHasta()));

		List<Vacaciones> entities = service.filterAndList(spec);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);

	}
}
