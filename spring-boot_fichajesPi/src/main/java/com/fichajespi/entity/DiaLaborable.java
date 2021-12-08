package com.fichajespi.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dias_laborables")
public class DiaLaborable {

	@Id
	@GeneratedValue()
	private Long id;

//	@NotNull
//	@Temporal(TemporalType.DATE)
//	private Date dia;
	@NotNull
	@Column(unique = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dia;

//	@NotNull
//	@Temporal(TemporalType.TIME)
//	private Date horaInicio;
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaInicio;

//	@NotNull
//	@Temporal(TemporalType.TIME)
//	private Date horaFin;
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaFin;

	@JsonIgnoreProperties(value = { "dias", "handler", "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendarioId")
	private Calendario calendario;

}
