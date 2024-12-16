/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class SoggettoResultSearch {
	private int idSoggetto;
	private String cognomeRagSociale;
	private String cfPiva;
	private String comune;
	private String telefono;
	private String mail;
	private int numDomande;
	
	public int getIdSoggetto() {
		return idSoggetto;
	}
	public String getCognomeRagSociale() {
		return cognomeRagSociale;
	}
	public String getCfPiva() {
		return cfPiva;
	}
	public String getComune() {
		return comune;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getMail() {
		return mail;
	}
	public int getNumDomande() {
		return numDomande;
	}
	public void setIdSoggetto(int idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public void setCognomeRagSociale(String cognomeRagSociale) {
		this.cognomeRagSociale = cognomeRagSociale;
	}
	public void setCfPiva(String cfPiva) {
		this.cfPiva = cfPiva;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setNumDomande(int numDomande) {
		this.numDomande = numDomande;
	}
	
	
	
}
