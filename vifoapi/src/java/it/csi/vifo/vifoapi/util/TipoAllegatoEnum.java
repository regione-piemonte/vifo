/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum TipoAllegatoEnum {
	DOMANDA_GRATUITA_CARICATA_AUTOMATICAMENTE(1),
	DOMANDA_GRATUITA_FIRMA_DIGITALE(2),
	DOMANDA_GRATUITA_FIRMA_AUTOGRAFA(3),
	DOMANDA_ONEROSA_CARICATA_AUTOMATICAMENTE(4),
	DOMANDA_ONEROSA_FIRMA_DIGITALE(5),
	DOMANDA_ONEROSA_FIRMA_AUTOGRAFA(6),
	ASSEGNAZIONE_GRATUITA_CARICATA_AUTOMATICAMENTE(7),
	ASSEGNAZIONE_GRATUITA_FIRMA_DIGITALE(8),
	ASSEGNAZIONE_GRATUITA_FIRMA_AUTOGRAFA(9),
	ASSEGNAZIONE_ONEROSA_CARICATA_AUTOMATICAMENTE(10),
	ACCETTAZIONE_FIRMA_DIGITALE(11),
	ACCETTAZIONE_FIRMA_AUTOGRAFA(12),
	SCANSIONE_DOCUMENTO_IDENTITA(13);
	
	private int value;
	
	private TipoAllegatoEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static TipoAllegatoEnum getEnum(int instanceTypeValue) {
		for (TipoAllegatoEnum instance : TipoAllegatoEnum.values()) { 
		    if(instance.getValue() == instanceTypeValue) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("TipoAllegatoEnum does not have enum with provided parameter");
	}
}
