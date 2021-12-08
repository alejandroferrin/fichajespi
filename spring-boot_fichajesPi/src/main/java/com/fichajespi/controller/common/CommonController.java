package com.fichajespi.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fichajespi.service.common.CommonService;

import io.swagger.annotations.ApiOperation;

public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;

//
//	@ApiOperation("Devuelve una lista con paginación de los objetos")
//	@GetMapping("/pages")
//	public ResponseEntity<Page<E>> pages(
//			@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "20") int size,
//			@RequestParam(defaultValue = "id") String order,
//			@RequestParam(defaultValue = "true") boolean asc) {
//
//		Page<E> entities = service.pages(
//				PageRequest.of(page, size, Sort.by(order)));
//		if (!asc)
//			entities = service.pages(
//					PageRequest.of(page, size, Sort.by(order).descending()));
//
//		return new ResponseEntity<Page<E>>(entities, HttpStatus.OK);
//
//	}

	@ApiOperation("Obtiene el objeto con el id indicado")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		E result = service.findById(id).orElse(null);
		if (result == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result);
	}

	@ApiOperation("Borra el objeto con el id indicado")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
