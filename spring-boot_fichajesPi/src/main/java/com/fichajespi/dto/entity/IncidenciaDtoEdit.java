package com.fichajespi.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaDtoEdit {

	private long id;
	private Boolean resuelta;
	private String explicacion;

}
