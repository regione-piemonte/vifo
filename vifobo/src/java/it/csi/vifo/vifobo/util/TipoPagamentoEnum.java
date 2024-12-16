/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

public enum TipoPagamentoEnum {
	PIANTINE(1),
	MARCA_BOLLO(2);
	
	private int value;
	
	private TipoPagamentoEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

}
