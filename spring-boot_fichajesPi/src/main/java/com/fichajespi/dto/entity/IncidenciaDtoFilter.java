package com.fichajespi.dto.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaDtoFilter {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaDesde;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaHasta;
	private Boolean resuelta;
	private String explicacion;
	private String resumen;

	private String usuarioNumero;
	private String usuarioNombre;
	private String usuarioDni;
	private String usuarioEmail;
}
