/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.validation;

import it.csi.vifo.vifoapi.util.CodiceEnum;

public abstract class BaseException extends RuntimeException {
	
	private CodiceEnum codice;
	private String testo;
		
	
	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause,CodiceEnum codice) {
		super(message,cause);
		this.codice=codice;
	}
	
	public CodiceEnum getCodice() {
		return codice;
	}
	public void setCodice(CodiceEnum codice) {
		this.codice = codice;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public abstract Object getPayload();
	
	
	
	
	
}