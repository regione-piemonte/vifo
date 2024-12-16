/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

import java.util.Date;

public class Destinazione {
	private Integer idCatasto;
	private Integer fkDomanda;
	private String istatComune;
	private String nomeComune;
	private String sezione;
	private Integer foglio;
	private String particella;
	private Double metriLineari;
	private Double metriQuadri;
	private Date dataInserimento;
	private Date dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdCatasto() {
		return idCatasto;
	}
	public Integer getFkDomanda() {
		return fkDomanda;
	}
	public String getIstatComune() {
		return istatComune;
	}
	public String getNomeComune() {
		return nomeComune;
	}
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	public String getSezione() {
		return sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public String getParticella() {
		return particella;
	}
	public Double getMetriLineari() {
		return metriLineari;
	}
	public Double getMetriQuadri() {
		return metriQuadri;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdCatasto(Integer idCatasto) {
		this.idCatasto = idCatasto;
	}
	public void setFkDomanda(Integer fkDomanda) {
		this.fkDomanda = fkDomanda;
	}
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public void setMetriLineari(Double metriLineari) {
		this.metriLineari = metriLineari;
	}
	public void setMetriQuadri(Double metriQuadri) {
		this.metriQuadri = metriQuadri;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	
	
	
}
