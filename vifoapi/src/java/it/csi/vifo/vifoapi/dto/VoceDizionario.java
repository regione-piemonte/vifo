/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class VoceDizionario {
	private Integer id;
	private String descrizione;
	
	public Integer getId() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	} 
	
	
}
