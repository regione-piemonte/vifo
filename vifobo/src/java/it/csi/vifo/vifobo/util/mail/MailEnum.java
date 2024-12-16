/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util.mail;

public enum MailEnum {
	
	INVIO_DOMANDA_GRATUITA_PROT(2),
	
	INVIO_DOMANDA_ONEROSA_PROT(5),
	
	INVIO_ACCETTAZIONE_ONEROSA_PROT(9);
	
	
	
	private int value;
	private MailEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
