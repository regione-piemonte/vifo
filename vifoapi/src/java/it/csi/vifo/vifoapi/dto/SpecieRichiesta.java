/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class SpecieRichiesta {
	
	private Integer idDomanda;
	private Integer idSpecie;
	private Integer qtaRichiesta;
	private String nomeComune;
	private String nomeScientifico;
	private Integer fkConfigUtente;
	
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public Integer getIdSpecie() {
		return idSpecie;
	}
	public Integer getQtaRichiesta() {
		return qtaRichiesta;
	}
	public String getNomeComune() {
		return nomeComune;
	}
	public String getNomeScientifico() {
		return nomeScientifico;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}
	public void setQtaRichiesta(Integer qtaRichiesta) {
		this.qtaRichiesta = qtaRichiesta;
	}
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	public void setNomeScientifico(String nomeScientifico) {
		this.nomeScientifico = nomeScientifico;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}

	
}
