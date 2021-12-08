/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.entity;

/**
 *
 * @author alex
 */
public class Fichaje {

	private String hora;
	private String dia;
	private String numeroUsuario;
	private String nombreUsuario;
	private String tipo;

	public Fichaje() {
	}

	public Fichaje(String hora, String dia, String numeroUsuario, String nombreUsuario, String tipo) {
		this.hora = hora;
		this.dia = dia;
		this.numeroUsuario = numeroUsuario;
		this.nombreUsuario = nombreUsuario;
		this.tipo = tipo;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getNumeroUsuario() {
		return numeroUsuario;
	}

	public void setNumeroUsuario(String numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
