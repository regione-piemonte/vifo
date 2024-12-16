/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum TipoPdfEnum {
	ASSEGNAZIONE_GRATUITA("Modello_Assegnazione_Gratuita"),
	ASSEGNAZIONE_ONEROSA("Modello_Assegnazione_Onerosa"),
	CERTIFICATO("Modello_certificato_provenienza"),
	DDT("Modello_DDT"),
	DOMANDA_GRATUITA("Modello_Domanda"),
	DOMANDA_ONEROSA("Modello_Domanda"),
	ETICHETTA("Modello_Etichetta"),
	ETICHETTA_QR_CODE("Modello_Etichetta_QRcode"),
	REGISTRO_CARICO_SCARICO("RegistroCaricoScarico"),
	TEST("Test");
	
	private String value;
	
	private TipoPdfEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
