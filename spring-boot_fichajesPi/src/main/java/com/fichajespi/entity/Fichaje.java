package com.fichajespi.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fichajes")
public class Fichaje {

	@Id
	@GeneratedValue
	private Long id;

//	@NotNull
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date tiempoFichaje;
	@NotNull
	@JsonFormat(pattern = "HH:mm")
	private LocalTime hora;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dia;

	@JsonIgnoreProperties(value = { "password", "dni", "diasVacaciones", "horasGeneradas", "working", "enVacaciones",
			"deBaja", "admin", "roles", "fichajes", "incidencias", "permisos", "vacaciones" })
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private TipoFichaje tipo;

	@NotNull
	private String tipo;

	@NotNull
	private String origen;
}
