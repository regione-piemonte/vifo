/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class DdtRiepilogo {
	private Integer idDomanda;
	private Integer numeroDdt;
	private Integer annoDdt;
	private String dataCreazione;
	private Integer idConfUtende;
	private TSoggetto operatore;
	private Integer qtaDdt;
	
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public Integer getNumeroDdt() {
		return numeroDdt;
	}
	public Integer getAnnoDdt() {
		return annoDdt;
	}
	public String getDataCreazione() {
		return dataCreazione;
	}
	public Integer getIdConfUtende() {
		return idConfUtende;
	}
	public TSoggetto getOperatore() {
		return operatore;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setNumeroDdt(Integer numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
	public void setAnnoDdt(Integer annoDdt) {
		this.annoDdt = annoDdt;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public void setIdConfUtende(Integer idConfUtende) {
		this.idConfUtende = idConfUtende;
	}
	public void setOperatore(TSoggetto operatore) {
		this.operatore = operatore;
	}
	public Integer getQtaDdt() {
		return qtaDdt;
	}
	public void setQtaDdt(Integer qtaDdt) {
		this.qtaDdt = qtaDdt;
	}
	
}
