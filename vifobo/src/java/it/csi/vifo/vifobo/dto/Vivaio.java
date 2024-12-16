/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

public class Vivaio {
	private Integer idVivaio;
	private String sigla;
	private String nome;
	private String indirizzo;
	private String cap;
	private String istatComune;
	private String telefono;
	private String mail;
	private String periodoApertura;
	private String orarioApertura;
	private String comunicazioni;
	public Integer getIdVivaio() {
		return idVivaio;
	}
	public String getSigla() {
		return sigla;
	}
	public String getNome() {
		return nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getCap() {
		return cap;
	}
	public String getIstatComune() {
		return istatComune;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getMail() {
		return mail;
	}
	public String getPeriodoApertura() {
		return periodoApertura;
	}
	public String getOrarioApertura() {
		return orarioApertura;
	}
	public String getComunicazioni() {
		return comunicazioni;
	}
	public void setIdVivaio(Integer idVivaio) {
		this.idVivaio = idVivaio;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPeriodoApertura(String periodoApertura) {
		this.periodoApertura = periodoApertura;
	}
	public void setOrarioApertura(String orarioApertura) {
		this.orarioApertura = orarioApertura;
	}
	public void setComunicazioni(String comunicazioni) {
		this.comunicazioni = comunicazioni;
	}
	
	
	@Override
	public String toString() {
		return "Vivaio [idVivaio=" + idVivaio + ", sigla=" + sigla + ", nome=" + nome + ", indirizzo=" + indirizzo
				+ ", cap=" + cap + ", istatComune=" + istatComune + ", telefono=" + telefono + ", mail=" + mail
				+ ", periodoApertura=" + periodoApertura + ", orarioApertura=" + orarioApertura + ", comunicazioni="
				+ comunicazioni + "]";
	}
	
	
}
