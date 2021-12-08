package com.fichajespi.dto.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private long id;

	private String email;
	private String password;
	private String numero;
	private String nombreEmpleado;
	private String dni;

	private List<String> roles;

	private Integer diasVacaciones;

	private Double horasGeneradas;

	private Boolean enVacaciones;

	private Boolean deBaja;

	private Boolean working;

}
