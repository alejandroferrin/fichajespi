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
public class DiaDto {

//	private Date dia;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Europe/Madrid")
//	private Date horaInicio;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss",timezone="Europe/Madrid" )
//	private Date horaFin;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dia;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaInicio;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaFin;
	private String calendarioNombre;

}
