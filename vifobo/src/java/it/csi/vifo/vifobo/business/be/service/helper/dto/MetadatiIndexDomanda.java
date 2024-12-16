/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexDomanda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2295263080305052807L;

	public static final String META_ID_ALLEGATO = "vifo:idAllegato";
	
	private String idAllegato;
	private String idDomanda;
	private String tipoAllegato;
	
	/**
	 * @return the idAllegato
	 */
	public String getIdAllegato() {
		return idAllegato;
	}
	/**
	 * @param idAllegato the idAllegato to set
	 */
	public void setIdAllegato(String idAllegato) {
		this.idAllegato = idAllegato;
	}
	public String getIdDomanda() {
		return idDomanda;
	}
	public String getTipoAllegato() {
		return tipoAllegato;
	}
	public void setIdDomanda(String idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setTipoAllegato(String tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}
	
	

}
