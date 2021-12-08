package com.fichajespi.dto.entity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioDto {

	private long id;
	@ApiModelProperty(value = "Nombre del calendario", dataType = "String", example = "calendario_2021", position = 1)
	private String nombre;
	private int year;
	// private boolean active;
	private int minutosMasEntrada;
	private int minutosMenosEntrada;
	private List<DiaDto> dias;

}
