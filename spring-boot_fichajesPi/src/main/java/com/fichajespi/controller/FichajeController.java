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
import com.fichajespi.dto.converter.FichajeDtoConverter;
import com.fichajespi.dto.entity.FichajeDto;
import com.fichajespi.dto.entity.FichajeDtoReqRes;
import com.fichajespi.entity.Fichaje;
import com.fichajespi.mail.EmailService;
import com.fichajespi.security.entity.RrhhDto;
import com.fichajespi.security.service.GetInfoFromToken;
import com.fichajespi.service.FichajeService;
import com.fichajespi.specifications.FichajeSpecifications;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fichaje")
//@CrossOrigin(origins = "http://localhost:4200")
public class FichajeController
		extends CommonController<Fichaje, FichajeService> {

	@Autowired
	FichajeDtoConverter dtoConverter;
	@Autowired
	FichajeSpecifications specifications;
	@Autowired
	GetInfoFromToken getInfoFromToken;

	@PostMapping("/now")
	public ResponseEntity<?> nuevoFichajeNow(
			@RequestBody FichajeDtoReqRes fichajeDto) {
		System.out.println("Fichaje");
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(dtoConverter.fichar(fichajeDto));

	}

	@ApiOperation("Obtiene una lista paginada y filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/pagesFiltered")
	public ResponseEntity<Page<Fichaje>> pageDtoSpec(
			@RequestBody FichajeDto dto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestHeader("authorization") String token) {

		RrhhDto tokenUser = getInfoFromToken.rrhhInfo(token);
		if (tokenUser.isRrhh()) {
			dto.setNumeroUsuario(tokenUser.getNumber());
		}

		Specification<Fichaje> spec = Specification
				.where(dto.getNombreUsuario() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getNombreUsuario()))
				.and(dto.getNumeroUsuario() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getNumeroUsuario()))
				.and(dto.getTipo() == null ? null
						: specifications.tipoContains(
								dto.getTipo()))
				.and(dto.getHoraDesde() == null ? null
						: specifications.horaMayorQue(
								dto.getHoraDesde()))
				.and(dto.getHoraHasta() == null ? null
						: specifications.horaMenorQue(
								dto.getHoraHasta()))
				.and(dto.getDiaDesde() == null ? null
						: specifications.diaMayorQue(
								dto.getDiaDesde()))
				.and(dto.getDiaHasta() == null ? null
						: specifications.diaMenorQue(
								dto.getDiaHasta()));

		Page<Fichaje> entities = service.pagesAndSpec(
				spec,
				PageRequest.of(page, size, Sort.by(order)));

		if (!asc)
			entities = service.pagesAndSpec(
					spec,
					PageRequest.of(page, size, Sort.by(order).descending()));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);
	}

	@ApiOperation("Obtiene una lista filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/listFiltered")
	public ResponseEntity<List<Fichaje>> filteredList(
			@RequestBody FichajeDto dto,
			@RequestHeader("authorization") String token) {

		RrhhDto tokenUser = getInfoFromToken.rrhhInfo(token);
		if (tokenUser.isRrhh()) {
			dto.setNumeroUsuario(tokenUser.getNumber());
		}

		Specification<Fichaje> spec = Specification
				.where(dto.getNombreUsuario() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getNombreUsuario()))
				.and(dto.getNumeroUsuario() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getNumeroUsuario()))
				.and(dto.getTipo() == null ? null
						: specifications.tipoContains(
								dto.getTipo()))
				.and(dto.getHoraDesde() == null ? null
						: specifications.horaMayorQue(
								dto.getHoraDesde()))
				.and(dto.getHoraHasta() == null ? null
						: specifications.horaMenorQue(
								dto.getHoraHasta()))
				.and(dto.getDiaDesde() == null ? null
						: specifications.diaMayorQue(
								dto.getDiaDesde()))
				.and(dto.getDiaHasta() == null ? null
						: specifications.diaMenorQue(
								dto.getDiaHasta()));

		List<Fichaje> entities = service.filterAndList(spec);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editFichaje(@RequestBody FichajeDto editar,
			@PathVariable Long id) {
		return service.findById(id).map(x -> {
			x.setDia(editar.getDia());
			x.setHora(editar.getHora());
			x.setTipo(editar.getTipo());
			return ResponseEntity.ok(service.save(x));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

}
