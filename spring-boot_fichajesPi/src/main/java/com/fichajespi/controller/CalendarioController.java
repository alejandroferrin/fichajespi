package com.fichajespi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fichajespi.controller.common.CommonController;
import com.fichajespi.dto.converter.CalendarioDtoConverter;
import com.fichajespi.dto.entity.CalendarioDto;
import com.fichajespi.dto.entity.Mensaje;
import com.fichajespi.entity.Calendario;
import com.fichajespi.entity.DiaLaborable;
import com.fichajespi.service.CalendarioService;
import com.fichajespi.service.DiaLaborableService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/calendario")
//@CrossOrigin(origins = "http://localhost:4200")
public class CalendarioController
		extends CommonController<Calendario, CalendarioService> {

	@Autowired
	CalendarioDtoConverter dtoConverter;
	@Autowired
	DiaController diaController;
	@Autowired
	DiaLaborableService diaService;

	@ApiOperation("Crea un nuevo calendario")
	@PostMapping("/create")
	public ResponseEntity<?> newCalendario(
			@RequestBody CalendarioDto calendarioDto) {
//		Calendario calendario = new Calendario();
//		calendario.setNombre(calendarioDto.getNombre());

		service.save(dtoConverter.transform(calendarioDto));
		diaController.newListaDias(calendarioDto.getDias());

//		return ResponseEntity.created(null).build();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Mensaje("Calendario creado"));

//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(service.save(dtoConverter.transform(calendarioDto)));
	}

	@ApiOperation("Devuelve una lista de DTO de calendarios")
	@GetMapping("/list/dto")
	public ResponseEntity<List<CalendarioDto>> listDto() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(service.list().stream()
						.map(c -> dtoConverter.inverseTransform(c))
						.collect(Collectors.toList()));

	}

	@ApiOperation("Edita un calendario")
	@PutMapping("/{id}")
	public ResponseEntity<?> editCalendario(@RequestBody Calendario editar,
			@PathVariable Long id) {

		return service.findById(id).map(c -> {
			c.setId(editar.getId());
			c.setYear(editar.getYear());
			c.setNombre(editar.getNombre());
			c.setMinutosMasEntrada(editar.getMinutosMasEntrada());
			c.setMinutosMenosEntrada(editar.getMinutosMenosEntrada());
			c.setActive(editar.isActive());
			editar.getDias().forEach(d -> {
				DiaLaborable dia = diaService.findById(d.getId()).get();
				dia.setDia(d.getDia());
				dia.setHoraInicio(d.getHoraInicio());
				dia.setHoraFin(d.getHoraFin());
				diaService.save(dia);
			});

			return ResponseEntity.ok(service.save(c));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

}
