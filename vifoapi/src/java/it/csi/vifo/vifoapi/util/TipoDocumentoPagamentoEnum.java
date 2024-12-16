/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum TipoDocumentoPagamentoEnum  {
	RICEVUTA_TELEMATICA("RT"),
	AVVISO_PAGAMENTO("AV"),
	MARCA_BOLLO("MB");
	
	private String codice;
	
	private TipoDocumentoPagamentoEnum(String codice) {
		this.codice = codice;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public static TipoDocumentoPagamentoEnum getEnum(String instanceTypeValue) {
		for (TipoDocumentoPagamentoEnum instance : TipoDocumentoPagamentoEnum.values()) { 
		    if(instance.getCodice().equalsIgnoreCase(instanceTypeValue)) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("TipoDomandaEnum does not have enum with provided parameter:" + instanceTypeValue);
	}

}
