/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class CertificatoProvAltro {
	Integer idProvenienza;
	String codice;
	Date dataCertificato;
	
	public Integer getIdProvenienza() {
		return idProvenienza;
	}
	public String getCodice() {
		return codice;
	}
	public Date getDataCertificato() {
		return dataCertificato;
	}
	public void setIdProvenienza(Integer idProvenienza) {
		this.idProvenienza = idProvenienza;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public void setDataCertificato(Date dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	
	
}
