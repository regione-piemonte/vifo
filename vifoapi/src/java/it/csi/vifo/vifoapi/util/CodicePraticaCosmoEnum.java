/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum CodicePraticaCosmoEnum {
	DOMANDA_GRATUITA("DG"),
	ASSEGNAZIONE_GRATUITA("AG"),
	DOMANDA_ONEROSA("DV"),
	ACCETTAZIONE("AV");
	
	
private String codice;
	
	private CodicePraticaCosmoEnum(String codice) {
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}
}
