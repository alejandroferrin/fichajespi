package com.fichajespi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fichajespi.entity.enums.EstadosPeticion;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permisos")
public class Permiso {

	@Id
	@GeneratedValue
	private Long id;

//	@NotNull
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date inicio;
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaInicio;

//	@NotNull
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date fin;
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private LocalTime horaFin;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dia;

	@NotNull
	private String descripcion;

//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private EstadosPeticion estado = EstadosPeticion.PENDIENTE;

	@NotNull
	private String estado = EstadosPeticion.PENDIENTE.toString();

	@Column(columnDefinition = "boolean default false")
	private boolean aprobado;

	@JsonIgnoreProperties(value = { "password", "dni", "diasVacaciones", "horasGeneradas",
			"working", "enVacaciones", "deBaja",
			"admin", "roles", "fichajes", "incidencias", "permisos", "vacaciones" })
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
