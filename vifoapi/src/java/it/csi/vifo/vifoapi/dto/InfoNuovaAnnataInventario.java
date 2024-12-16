/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InfoNuovaAnnataInventario {
	private Integer idVivaio;
	private String vivaioDescr;
	private Integer numPartitePresenti;
	private Integer numPartiteNotEmpty;
	private Integer numPartiteToCreate;
	
	public Integer getIdVivaio() {
		return idVivaio;
	}
	public String getVivaioDescr() {
		return vivaioDescr;
	}
	public Integer getNumPartitePresenti() {
		return numPartitePresenti;
	}
	public Integer getNumPartiteNotEmpty() {
		return numPartiteNotEmpty;
	}
	public Integer getNumPartiteToCreate() {
		return numPartiteToCreate;
	}
	public void setIdVivaio(Integer idVivaio) {
		this.idVivaio = idVivaio;
	}
	public void setVivaioDescr(String vivaioDescr) {
		this.vivaioDescr = vivaioDescr;
	}
	public void setNumPartitePresenti(Integer numPartitePresenti) {
		this.numPartitePresenti = numPartitePresenti;
	}
	public void setNumPartiteNotEmpty(Integer numPartiteNotEmpty) {
		this.numPartiteNotEmpty = numPartiteNotEmpty;
	}
	public void setNumPartiteToCreate(Integer numPartiteToCreate) {
		this.numPartiteToCreate = numPartiteToCreate;
	}
	
	

}
