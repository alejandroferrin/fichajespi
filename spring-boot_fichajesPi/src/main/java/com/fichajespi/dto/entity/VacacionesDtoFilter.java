package com.fichajespi.dto.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacacionesDtoFilter {

	private String estado;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioDesde;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioHasta;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate finDesde;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate finHasta;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Boolean consumidas;
	private Boolean aprobado;
	private String usuarioNumero;
	private String usuarioNombre;
	private String usuarioDni;
	private String usuarioEmail;

}
