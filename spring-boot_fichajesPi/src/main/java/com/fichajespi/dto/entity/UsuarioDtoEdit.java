package com.fichajespi.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoEdit {


	private String email;
	private String password;
	private String numero;
	private String nombreEmpleado;
	private String dni;

	private Integer diasVacaciones;

	private Double horasGeneradas;

	private Boolean enVacaciones;

	private Boolean deBaja;

	private Boolean working;

}
