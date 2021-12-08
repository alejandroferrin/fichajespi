package com.fichajespi.dto.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacacionesDto {


//	private Date dia;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Europe/Madrid")
//	private Date horaInicio;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss",timezone="Europe/Madrid" )
//	private Date horaFin;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicio;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fin;
	private String numeroUsuario;
	private String nombreUsuario;

}
