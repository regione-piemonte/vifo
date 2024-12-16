/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class PdfTesto {
	private String testo;
	private String style;
	
	public PdfTesto(String testo, String style) {
		super();
		this.testo = testo;
		this.style = style;
	}
	public String getTesto() {
		return testo;
	}
	public String getStyle() {
		return style;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
