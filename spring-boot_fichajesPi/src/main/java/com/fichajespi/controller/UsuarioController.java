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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fichajespi.controller.common.CommonController;
import com.fichajespi.dto.converter.UsuarioDtoConverter;
import com.fichajespi.dto.entity.Mensaje;
import com.fichajespi.dto.entity.UsuarioDto;
import com.fichajespi.dto.entity.UsuarioDtoEdit;
import com.fichajespi.dto.entity.UsuarioDtoEditPassword;
import com.fichajespi.dto.entity.UsuarioDtoFilter;
import com.fichajespi.entity.Usuario;
import com.fichajespi.security.jwt.JwtProvider;
import com.fichajespi.service.UsuarioService;
import com.fichajespi.specifications.UsuarioSpecifications;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController
		extends CommonController<Usuario, UsuarioService> {

	@Autowired
	UsuarioDtoConverter dtoConverter;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UsuarioSpecifications specifications;

	@ApiOperation("Obtiene una lista paginada y filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/pagesFiltered")
	public ResponseEntity<Page<UsuarioDto>> pageDtoSpec(
			@RequestBody UsuarioDtoFilter dto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc) {

		Specification<Usuario> spec = Specification
				.where(dto.getNombreEmpleado() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getNombreEmpleado()))
				.and(dto.getEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getEmail()))
				.and(dto.getNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getNumero()))
				.and(dto.getDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getDni()))
				.and(dto.getWorking() == null ? null
						: specifications.isWorking(
								dto.getWorking()))
				.and(dto.getEnVacaciones() == null ? null
						: specifications.isEnVacaciones(
								dto.getEnVacaciones()))
				.and(dto.getDeBaja() == null ? null
						: specifications.isDeBaja(
								dto.getDeBaja()))
				.and(dto.getDiasVacacionesDesde() == null ? null
						: specifications.diasDesde(
								dto.getDiasVacacionesDesde()))
				.and(dto.getDiasVacacionesHasta() == null ? null
						: specifications.diasHasta(
								dto.getDiasVacacionesHasta()))
				.and(dto.getHorasGeneradasDesde() == null ? null
						: specifications.horasDesde(
								dto.getHorasGeneradasDesde()))
				.and(dto.getHorasGeneradasHasta() == null ? null
						: specifications.horasHasta(
								dto.getHorasGeneradasHasta()));

		Page<Usuario> entities = service.pagesAndSpec(
				spec,
				PageRequest.of(page, size, Sort.by(order)));

		if (!asc)
			entities = service.pagesAndSpec(
					spec,
					PageRequest.of(page, size, Sort.by(order).descending()));

		Page<UsuarioDto> entitiesDto = entities
				.map(usu -> dtoConverter.inverseTransform(usu));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entitiesDto);

	}

	@ApiOperation("Obtiene una lista filtrada de objetos, el filtro se realiza a través de un DTO de ejemplo")
	@PostMapping("/listFiltered")
	public ResponseEntity<List<Usuario>> filteredList(@RequestBody UsuarioDtoFilter dto) {

		Specification<Usuario> spec = Specification
				.where(dto.getNombreEmpleado() == null ? null
						: specifications.nombreUsuarioContains(
								dto.getNombreEmpleado()))
				.and(dto.getEmail() == null ? null
						: specifications.emailUsuarioContains(
								dto.getEmail()))
				.and(dto.getNumero() == null ? null
						: specifications.numeroUsuarioContains(
								dto.getNumero()))
				.and(dto.getDni() == null ? null
						: specifications.dniUsuarioContains(
								dto.getDni()))
				.and(dto.getWorking() == null ? null
						: specifications.isWorking(
								dto.getWorking()))
				.and(dto.getEnVacaciones() == null ? null
						: specifications.isEnVacaciones(
								dto.getEnVacaciones()))
				.and(dto.getDeBaja() == null ? null
						: specifications.isDeBaja(
								dto.getDeBaja()))
				.and(dto.getDiasVacacionesDesde() == null ? null
						: specifications.diasDesde(
								dto.getDiasVacacionesDesde()))
				.and(dto.getDiasVacacionesHasta() == null ? null
						: specifications.diasHasta(
								dto.getDiasVacacionesHasta()))
				.and(dto.getHorasGeneradasDesde() == null ? null
						: specifications.horasDesde(
								dto.getHorasGeneradasDesde()))
				.and(dto.getHorasGeneradasHasta() == null ? null
						: specifications.horasHasta(
								dto.getHorasGeneradasHasta()));

		List<Usuario> entities = service.filterAndList(spec);

//		List<UsuarioDto> entitiesDto = (List<UsuarioDto>) entities.stream()
//				.map(usu -> dtoConverter.inverseTransform(usu));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(entities);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editUser(@RequestBody UsuarioDtoEdit editar,
			@PathVariable Long id) {

		return service.findById(id).map(d -> {

			dtoConverter.transformEdit(d, editar);
			return ResponseEntity.ok(service.save(d));

		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

	@PutMapping("password/{id}")
	public ResponseEntity<?> editUserPassword(@RequestBody UsuarioDtoEditPassword editar,
			// @RequestHeader Map<String, String> headers,
			@RequestHeader("authorization") String token,
			@PathVariable Long id) {

		token = token.replace("Bearer ", "");
		Usuario usuario = service.findById(id).orElse(null);
		if (usuario != null) {

			if (jwtProvider.validateToken(token)
					&& jwtProvider.getSubjectFromToken(token).equals(usuario.getNumero())) {

				usuario = dtoConverter.transformEditPassword(usuario, editar);
				return ResponseEntity.ok(service.save(usuario));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new Mensaje("No puedes cambiar la contraseña de otro usuario"));
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/suma_vacaciones_plantilla/{dias}")
	public ResponseEntity<?> sumarVacacionesPlantilla(@PathVariable int dias) {
		service.list().stream().forEach(u -> {
			u.setDiasVacaciones(u.getDiasVacaciones() + dias);
			service.save(u);
		});
		return ResponseEntity.ok().build();
	}

	@ApiOperation("Usuario obtiene la información de su usario")
	@GetMapping("/miusuario")
	public ResponseEntity<?> getYourUser(@RequestHeader("authorization") String token) {
		token = token.replace("Bearer ", "");
		if (jwtProvider.validateToken(token)) {
			String numeroUsuario = jwtProvider.getSubjectFromToken(token);
			Usuario usuario = service.findByNumero(numeroUsuario).orElse(null);
			if (usuario != null) {
				return ResponseEntity.ok(usuario);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new Mensaje("Solo puedes acceder a la información de tú usuario"));
		}
	}
}
