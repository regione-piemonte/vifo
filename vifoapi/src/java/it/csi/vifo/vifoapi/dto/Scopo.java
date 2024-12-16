/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class Scopo {
	
	private Integer idScopo;
	private String descrizione;
	
	public Integer getIdScopo() {
		return idScopo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setIdScopo(Integer idScopo) {
		this.idScopo = idScopo;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
}
