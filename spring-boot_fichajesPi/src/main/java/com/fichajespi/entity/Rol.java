package com.fichajespi.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fichajespi.security.enums.RolNombre;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;
}
