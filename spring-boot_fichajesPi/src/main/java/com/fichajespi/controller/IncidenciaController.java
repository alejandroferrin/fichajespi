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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fichajespi.controller.common.CommonController;
import com.fichajespi.dto.converter.IncidenciaDtoConverter;
import com.fichajespi.dto.entity.ChartDataDto;
import com.fichajespi.dto.entity.IncidenciaDtoEdit;
import com.fichajespi.dto.entity.IncidenciaDtoFilter;
import com.fichajespi.dto.interfaces.ITopIncidencias;
import com.fichajespi.dto.interfaces.IUsuarioDtoEstadistica;
import com.fichajespi.entity.Incidencia;
import com.fichajespi.service.IncidenciaService;
import com.fichajespi.specifications.IncidenciaSpecifications;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/incidencia")
//@CrossOrigin(origins = "http://localhost:4200")
public class IncidenciaController
		extends CommonController<Incidencia, IncidenciaService> {

//	@Autowired
//	IncidenciaDtoConverter dtoConverter;
	@Autowired
	IncidenciaSpecifications specifications;
	@Autowired
	IncidenciaDtoConverter dtoConverter;

//	@ApiOperation("Obtiene una lista de objetos dado un id de usuario")
//	@GetMapping("/usuario/{id}")
//	public ResponseEntity<?> getByUsuarioId(@PathVariable Long id) {
//		List<Incidencia> result = service.findByUser(id);
//		if (result == null)
//			return ResponseEntity.notFound().build();
//		else
//			return ResponseEntity.ok(result);
//	}

	@ApiOperation("Obtiene una lista paginada y filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/pagesFiltered")
	public ResponseEntity<Page<Incidencia>> pageDtoSpec(
			@RequestBody IncidenciaDtoFilter dto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc) {

		Specification<Incidencia> spec = Specification
				.where(dto.getUsuarioNombre() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getUsuarioNombre()))
				.and(dto.getUsuarioEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getUsuarioEmail()))
				.and(dto.getUsuarioNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getUsuarioNumero()))
				.and(dto.getUsuarioDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getUsuarioDni()))
				.and(dto.getExplicacion() == null ? null
						: specifications.explicacionContains(
								dto.getExplicacion()))
				.and(dto.getResumen() == null ? null
						: specifications.resumenContains(
								dto.getResumen()))
				.and(dto.getResuelta() == null ? null
						: specifications.isResuelta(
								dto.getResuelta()))
				.and(dto.getDiaDesde() == null ? null
						: specifications.diaDesde(
								dto.getDiaDesde()))
				.and(dto.getDiaHasta() == null ? null
						: specifications.diaHasta(
								dto.getDiaHasta()));

		Page<Incidencia> entities = service.pagesAndSpec(
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
	public ResponseEntity<List<Incidencia>> filteredList(@RequestBody IncidenciaDtoFilter dto) {

		Specification<Incidencia> spec = Specification
				.where(dto.getUsuarioNombre() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getUsuarioNombre()))
				.and(dto.getUsuarioEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getUsuarioEmail()))
				.and(dto.getUsuarioNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getUsuarioNumero()))
				.and(dto.getUsuarioDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getUsuarioDni()))
				.and(dto.getExplicacion() == null ? null
						: specifications.explicacionContains(
								dto.getExplicacion()))
				.and(dto.getResumen() == null ? null
						: specifications.resumenContains(
								dto.getResumen()))
				.and(dto.getResuelta() == null ? null
						: specifications.isResuelta(
								dto.getResuelta()))
				.and(dto.getDiaDesde() == null ? null
						: specifications.diaDesde(
								dto.getDiaDesde()))
				.and(dto.getDiaHasta() == null ? null
						: specifications.diaHasta(
								dto.getDiaHasta()));

		List<Incidencia> entities = service.filterAndList(spec);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editIncidencia(@RequestBody IncidenciaDtoEdit editar,
			@PathVariable Long id) {

		return service.findById(id).map(d -> {

			dtoConverter.transformEdit(d, editar);
			return ResponseEntity.ok(service.save(d));

		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

	@ApiOperation("Obtiene el número de incidencias de los últimos 12 meses")
	@GetMapping("/count")
	public ResponseEntity<?> countLast12Months() {
		ChartDataDto result = service.numberOfIncidenciasLast12Months();
		return ResponseEntity.ok(result);
	}

	@ApiOperation("Obtiene el número de incidencias por usuario de los últimos 12 meses")
	@GetMapping("/count/users")
	public ResponseEntity<?> countUsersLast12Months() {
		List<IUsuarioDtoEstadistica> result = service.numberOfIncidenciasPerUserLast12Months();
		return ResponseEntity.ok(result);
	}

	@ApiOperation("Obtiene el número de incidencias agrupadas por resumen de los últimos 12 meses")
	@GetMapping("/count/top")
	public ResponseEntity<?> topIncidenciasLast12Months() {
		List<ITopIncidencias> result = service.topIncidenciasLast12Months();
		return ResponseEntity.ok(result);
	}
}
