/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexFotoSpecie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2285408933626327972L;
	
	private String idFotoSpecie;
	private String idSpecie;
	private String idTipoSpecie;
	
	public static final String META_ID_FOTO_SPECIE = "vifo:idFotoSpecie";
	
	public static final String FOLDER_INTERVENTI = "INTERVENTI";

	public String getIdSpecie() {
		return idSpecie;
	}

	public String getIdTipoSpecie() {
		return idTipoSpecie;
	}

	public void setIdSpecie(String idSpecie) {
		this.idSpecie = idSpecie;
	}

	public void setIdTipoSpecie(String idTipoSpecie) {
		this.idTipoSpecie = idTipoSpecie;
	}

	public String getIdFotoSpecie() {
		return idFotoSpecie;
	}

	public void setIdFotoSpecie(String idFotoSpecie) {
		this.idFotoSpecie = idFotoSpecie;
	}

	
	
}
