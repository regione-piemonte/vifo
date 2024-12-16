/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum TipoDomandaEnum {
	DOMANDA_ASSEGNAZIONE_GRATUITA(1),
	DOMANDA_ASSEGNAZIONE_ONEROSA(2);
	
	private int value;
	
	private TipoDomandaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
