package com.fichajespi.dto.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermisoDtoFilter {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaDesde;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaHasta;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaInicioDesde;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaInicioHasta;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaFinDesde;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaFinHasta;
	@JsonFormat(pattern = "HH:mm")
	private String descripcion;
	private String estado;

	private String usuarioNumero;
	private String usuarioNombre;
	private String usuarioDni;
	private String usuarioEmail;
}
