/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum ExcelCaricoScaricoEnum {
	ID_INVENTARIO("ID Inv."),
	DATA_MOVIMENTO("Data movimento"),
	SPECIE("Specie"),
	PROVENIENZA("Provenienza"),
	CERTIFICATO_PROV("Certificato Provenienza"),
	QUANTITA_CARICO("Quantita' carico"),
	QUANTITA_SCARICO("Quantita' scarico"),
	ID_DOMANDA("Id Domanda"),
	NUMERO_DDT("Numero Ddt"),
	TIPO_DOMANDA("Tipo Domanda"),	
	RICHIEDENTE("Richiedente/Destinatario");
	
private String value;
	
	ExcelCaricoScaricoEnum(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
