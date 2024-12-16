/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class PdfAttitudine {
	private String nomeSpecie;
	private String altezzaMinMax;
	private String attitudini;
	public String getNomeSpecie() {
		return nomeSpecie;
	}
	public String getAltezzaMinMax() {
		return altezzaMinMax;
	}
	public String getAttitudini() {
		return attitudini;
	}
	public void setNomeSpecie(String nomeSpecie) {
		this.nomeSpecie = nomeSpecie;
	}
	public void setAltezzaMinMax(String altezzaMinMax) {
		this.altezzaMinMax = altezzaMinMax;
	}
	public void setAttitudini(String attitudini) {
		this.attitudini = attitudini;
	}
	
	
}
