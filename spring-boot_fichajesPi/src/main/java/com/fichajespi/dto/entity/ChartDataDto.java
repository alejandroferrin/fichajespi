package com.fichajespi.dto.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataDto {

	private List<LocalDate> fechas;
	private List<Integer> cantidades;

}
