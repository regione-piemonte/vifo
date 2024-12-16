/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum SignatureTypeEnum {
	PCKS("PCKS"), CPDF("@cPDF"), XML("XML"), UNSIGNED("UNSIGNED");

	private String value;
	
	private SignatureTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
