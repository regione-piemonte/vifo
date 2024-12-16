/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum StatoDomandaEnum {
	BOZZA(1),
	INVIATA(2),
	PREASSEGNATA(3),
	ASSEGNATA(4),
	ACCETTATA(5),
	RICHIESTA_VARIAZIONI(6),
	RITIRO_IN_CORSO(7),
	RIFIUTATA(8),
	CHIUSA(9);
	
	private int value;
	
	private StatoDomandaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
