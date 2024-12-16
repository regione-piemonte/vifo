/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class SoggettoSearch {
	private String cognome;
	private String ragoneSociale;
	private String codFiscalePiva;
	private String provincia;
	private String comune;
	private Integer idVivaio;
	
	public String getCognome() {
		return cognome;
	}
	public String getRagoneSociale() {
		return ragoneSociale;
	}
	public String getCodFiscalePiva() {
		return codFiscalePiva;
	}
	public String getProvincia() {
		return provincia;
	}
	public String getComune() {
		return comune;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setRagoneSociale(String ragoneSociale) {
		this.ragoneSociale = ragoneSociale;
	}
	public void setCodFiscalePiva(String codFiscalePiva) {
		this.codFiscalePiva = codFiscalePiva;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public Integer getIdVivaio() {
		return idVivaio;
	}
	public void setIdVivaio(Integer idVivaio) {
		this.idVivaio = idVivaio;
	}
	
	
}
