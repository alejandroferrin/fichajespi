package com.fichajespi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Vacaciones {

	@Id
	@GeneratedValue
	private Long id;

//	@NotNull
//	@Temporal(TemporalType.DATE)
//	private Date inicio;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicio;

//	@NotNull
//	@Temporal(TemporalType.DATE)
//	private Date fin;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fin;

//	@Column(columnDefinition = "boolean default false")
	private Boolean consumidas;

	@NotNull
//	@Enumerated(EnumType.STRING)
//	private EstadosPeticion estado = EstadosPeticion.PENDIENTE;
	private String estado = EstadosPeticion.PENDIENTE.toString();

	@Column(columnDefinition = "boolean default false")
	private Boolean aprobado;

	@JsonIgnoreProperties(value = { "password", "dni", "diasVacaciones", "horasGeneradas",
			"working", "enVacaciones", "deBaja",
			"admin", "roles", "fichajes", "incidencias", "permisos", "vacaciones" })
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
