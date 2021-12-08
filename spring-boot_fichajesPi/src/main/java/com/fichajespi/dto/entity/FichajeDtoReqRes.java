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
public class FichajeDtoReqRes {

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Madrid")
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime hora;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dia;
	private String numeroUsuario;
	private String nombreUsuario;
	private String tipo;
	private String origen;

}
