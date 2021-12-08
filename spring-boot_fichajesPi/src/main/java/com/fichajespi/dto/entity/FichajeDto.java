package com.fichajespi.dto.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FichajeDto {

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Madrid")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hora;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaDesde;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaHasta;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dia;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaDesde;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate diaHasta;
	private String numeroUsuario;
	private String nombreUsuario;
	private String tipo;
	private String origen;

}
