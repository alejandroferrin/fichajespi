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
public class NumeroEmpleado {
	
	private String numeroUsuario;
	private String origen;
	
	public NumeroEmpleado() {
	}

	public NumeroEmpleado(String numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
		this.origen = "tarjeta";
	}

	public String getNumeroUsuario() {
		return numeroUsuario;
	}

	public void setNumeroUsuario(String numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	
	
	
}
