/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InventarioFilter {
	private int idVivaio;

	private Integer numeroPartita;
	private String specie;
	private int annata;
	private boolean attive;
	
	
	
	public InventarioFilter(String specie, int annata, boolean attive) {
		super();
		this.specie = specie;
		this.annata = annata;
		this.attive = attive;
	}
	public InventarioFilter(int idVivaio, String specie, int annata, boolean attive) {
		super();
		this.idVivaio = idVivaio;
		this.specie = specie;
		this.annata = annata;
		this.attive = attive;
	}

	public InventarioFilter(int idVivaio, Integer numeroPartita, String specie, int annata, boolean attive) {
		this.idVivaio = idVivaio;
		this.numeroPartita = numeroPartita;
		this.specie = specie;
		this.annata = annata;
		this.attive = attive;
	}
	public InventarioFilter(Integer numeroPartita, String specie, int annata, boolean attive) {
		this.numeroPartita = numeroPartita;
		this.specie = specie;
		this.annata = annata;
		this.attive = attive;
	}

	public int getIdVivaio() {
		return idVivaio;
	}
	public void setIdVivaio(int idVivaio) {
		this.idVivaio = idVivaio;
	}
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public int getAnnata() {
		return annata;
	}
	public void setAnnata(int annata) {
		this.annata = annata;
	}
	public boolean isAttive() {
		return attive;
	}
	public void setAttive(boolean attive) {
		this.attive = attive;
	}

	public Integer getNumeroPartita() {
		return numeroPartita;
	}

	public void setNumeroPartita(Integer numeroPartita) {
		this.numeroPartita = numeroPartita;
	}
}
