/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class RicercaDdt {
	private Integer numeroDdt;
	private Integer annoDdt;
	private Integer idDomanda;
	private String cognome;
	private String denominazione;
	private Integer idVivaio;
	
	public Integer getNumeroDdt() {
		return numeroDdt;
	}
	public Integer getAnnoDdt() {
		return annoDdt;
	}
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public String getCognome() {
		return cognome;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public Integer getIdVivaio() {
		return idVivaio;
	}
	public void setNumeroDdt(Integer numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
	public void setAnnoDdt(Integer annoDdt) {
		this.annoDdt = annoDdt;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public void setIdVivaio(Integer idVivaio) {
		this.idVivaio = idVivaio;
	}

}
