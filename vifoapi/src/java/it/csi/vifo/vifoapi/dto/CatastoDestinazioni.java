/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class CatastoDestinazioni {
	private Integer idCatasto;
	private Integer fkDomanda;
	private String istatComune;
	private String sezione;
	private Integer foglio;
	private String particella;
	private Double metriLineari;
	private Double metriQuadri;
	private Date dataInserimento;
	private Date dataAggiornamneto;
	private Integer fkConfUtente;
	public Integer getIdCatasto() {
		return idCatasto;
	}
	public Integer getFkDomanda() {
		return fkDomanda;
	}
	public String getIstatComune() {
		return istatComune;
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
	public Date getDataAggiornamneto() {
		return dataAggiornamneto;
	}
	public Integer getFkConfUtente() {
		return fkConfUtente;
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
	public void setDataAggiornamneto(Date dataAggiornamneto) {
		this.dataAggiornamneto = dataAggiornamneto;
	}
	public void setFkConfUtente(Integer fkConfUtente) {
		this.fkConfUtente = fkConfUtente;
	}
	
	
}
