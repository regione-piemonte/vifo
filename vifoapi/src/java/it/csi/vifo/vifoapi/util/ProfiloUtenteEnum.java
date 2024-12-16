/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum ProfiloUtenteEnum {
	NO_DATA(0),
	RICHIEDENTE(1),
	VIVAIO(2),
	GESTORE(3);
	
	private int value;
	
	private ProfiloUtenteEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
