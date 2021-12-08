package com.fichajespi.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoFilter {

	private String email;
	private String numero;
	private String nombreEmpleado;
	private String dni;

	private Integer diasVacacionesDesde;
	private Integer diasVacacionesHasta;

	private Double horasGeneradasDesde;
	private Double horasGeneradasHasta;

	private Boolean enVacaciones;

	private Boolean deBaja;

	private Boolean working;

}
