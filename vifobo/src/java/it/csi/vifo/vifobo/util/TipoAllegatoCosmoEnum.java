/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

public enum TipoAllegatoCosmoEnum {
	DOMANDA_GRATUITA(new String[] {"1","2","3"}),
	ASSEGNAZIONE_GRATUITA(new String[] {"7","8","9"}),
	DOMANDA_VENDITA(new String[] {"5","6"}),
	ACCETTAZIONE_VENDITA(new String[] {"11","12"});
	
	private String[] ids;
	
	private TipoAllegatoCosmoEnum(String[] values) {
		this.ids = values;
	}

	public String[] getIds() {
		return ids;
	}
	
}
