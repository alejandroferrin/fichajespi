package com.fichajespi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calendarios")
public class Calendario {

	@Id
	@GeneratedValue()
	private Long id;

	@NotNull
	@Column(unique = true)
	private String nombre;

	@Column(columnDefinition = "boolean default false")
	private boolean active;

	@NotNull
	@Column(unique = true)
	private int year;

	@NotNull
	private int minutosMasEntrada;

	@NotNull
	private int minutosMenosEntrada;

	@JsonIgnoreProperties(value = { "calendario" })
	@OneToMany(mappedBy = "calendario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DiaLaborable> dias;
}
