/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class Ddt {
	private Integer idDdt;
	private Integer numeroDdt;
	private Integer annoDdt;
	private Date dataInserimento;
	private Date dataAggiornamento;
	private Integer fkConfigUtente;	
	private String specie;
	private Integer idAssegnata;
	private Integer idPartita;
	private String Vivaio;
	private String allevamento;
	private String eta;
	private String altezza;
	private String certificato;
	private Integer qtaAssegnataRimanente;
	private Integer qtaInDdt;
	private String numeroFatturaAcconto;
	private Date dataFatturaAcconto;
	
	public Integer getIdDdt() {
		return idDdt;
	}
	public Integer getNumeroDdt() {
		return numeroDdt;
	}
	public Integer getAnnoDdt() {
		return annoDdt;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public String getSpecie() {
		return specie;
	}
	public Integer getIdAssegnata() {
		return idAssegnata;
	}
	public Integer getIdPartita() {
		return idPartita;
	}
	public String getVivaio() {
		return Vivaio;
	}
	public String getAllevamento() {
		return allevamento;
	}
	public String getEta() {
		return eta;
	}
	public String getAltezza() {
		return altezza;
	}
	public String getCertificato() {
		return certificato;
	}
	public Integer getQtaAssegnataRimanente() {
		return qtaAssegnataRimanente;
	}
	public Integer getQtaInDdt() {
		return qtaInDdt;
	}
	public void setIdDdt(Integer idDdt) {
		this.idDdt = idDdt;
	}
	public void setNumeroDdt(Integer numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
	public void setAnnoDdt(Integer annoDdt) {
		this.annoDdt = annoDdt;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public void setIdAssegnata(Integer idAssegnata) {
		this.idAssegnata = idAssegnata;
	}
	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}
	public void setVivaio(String Vivaio) {
		this.Vivaio = Vivaio;
	}
	public void setAllevamento(String allevamento) {
		this.allevamento = allevamento;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}
	public void setCertificato(String certificato) {
		this.certificato = certificato;
	}
	public void setQtaAssegnataRimanente(Integer qtaAssegnataRimanente) {
		this.qtaAssegnataRimanente = qtaAssegnataRimanente;
	}
	public void setQtaInDdt(Integer qtaInDdt) {
		this.qtaInDdt = qtaInDdt;
	}
	public String getNumeroFatturaAcconto() {
		return numeroFatturaAcconto;
	}
	public void setNumeroFatturaAcconto(String numeroFatturaAcconto) {
		this.numeroFatturaAcconto = numeroFatturaAcconto;
	}
	public Date getDataFatturaAcconto() {
		return dataFatturaAcconto;
	}
	public void setDataFatturaAcconto(Date dataFatturaAcconto) {
		this.dataFatturaAcconto = dataFatturaAcconto;
	}
	
}
