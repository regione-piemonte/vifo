/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto.enumeration;

public enum CertificatoTypeEnum {
	CALCOLATO("C"),
	LIBERO("L"),
	NO_FINI_FORESTALI("N"),
	ALTRA_SPECIE("A");
	
	private String value;
	
	private CertificatoTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
