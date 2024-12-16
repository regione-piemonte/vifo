/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class BaseDdt {
	private Integer numeroDdt;
	private Integer annoDdt;
	private String dataCreazione;
	private Integer qtaDdt;
	
	public Integer getNumeroDdt() {
		return numeroDdt;
	}
	public Integer getAnnoDdt() {
		return annoDdt;
	}
	public String getDataCreazione() {
		return dataCreazione;
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
	public Integer getQtaDdt() {
		return qtaDdt;
	}
	public void setQtaDdt(Integer qtaDdt) {
		this.qtaDdt = qtaDdt;
	}
	
	


	
	
	
}
