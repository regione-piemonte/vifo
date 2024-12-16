/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class TrasferimentoPartita {
	private Integer idPartita;
	private Integer fkPartitaOriginaria;
	private String specie;
	private String vivaioDestinazioneDescr;
	private Integer fkVivaioDestinazione;
	private String vivaioOrigineDescr;
	private Integer fkVivaioOrigine;
	private String dataNotifica;
	private Integer qtaTrasferita;
	private String tipoNotifica;
	private String vivaioNotificaDescr;
	private String stato;
	private String flgTipoTrasferimento;
	private String flgValidTrasferimento;
	private Integer fkVivaioUtente;
	private Integer fkConfUtente;
	
	public Integer getIdPartita() {
		return idPartita;
	}
	public Integer getFkPartitaOriginaria() {
		return fkPartitaOriginaria;
	}
	public String getSpecie() {
		return specie;
	}
	public String getVivaioDestinazioneDescr() {
		return vivaioDestinazioneDescr;
	}
	public Integer getFkVivaioDestinazione() {
		return fkVivaioDestinazione;
	}
	public String getVivaioOrigineDescr() {
		return vivaioOrigineDescr;
	}
	public Integer getFkVivaioOrigine() {
		return fkVivaioOrigine;
	}
	public String getDataNotifica() {
		return dataNotifica;
	}
	public Integer getQtaTrasferita() {
		return qtaTrasferita;
	}
	public String getTipoNotifica() {
		return tipoNotifica;
	}
	public String getVivaioNotificaDescr() {
		return vivaioNotificaDescr;
	}
	public String getStato() {
		return stato;
	}
	public String getFlgTipoTrasferimento() {
		return flgTipoTrasferimento;
	}
	public String getFlgValidTrasferimento() {
		return flgValidTrasferimento;
	}
	public Integer getFkVivaioUtente() {
		return fkVivaioUtente;
	}
	public Integer getFkConfUtente() {
		return fkConfUtente;
	}
	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}
	public void setFkPartitaOriginaria(Integer fkPartitaOriginaria) {
		this.fkPartitaOriginaria = fkPartitaOriginaria;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public void setVivaioDestinazioneDescr(String vivaioDestinazioneDescr) {
		this.vivaioDestinazioneDescr = vivaioDestinazioneDescr;
	}
	public void setFkVivaioDestinazione(Integer fkVivaioDestinazione) {
		this.fkVivaioDestinazione = fkVivaioDestinazione;
	}
	public void setVivaioOrigineDescr(String vivaioOrigineDescr) {
		this.vivaioOrigineDescr = vivaioOrigineDescr;
	}
	public void setFkVivaioOrigine(Integer fkVivaioOrigine) {
		this.fkVivaioOrigine = fkVivaioOrigine;
	}
	public void setDataNotifica(String dataNotifica) {
		this.dataNotifica = dataNotifica;
	}
	public void setQtaTrasferita(Integer qtaTrasferita) {
		this.qtaTrasferita = qtaTrasferita;
	}
	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}
	public void setVivaioNotificaDescr(String vivaioNotificaDescr) {
		this.vivaioNotificaDescr = vivaioNotificaDescr;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setFlgTipoTrasferimento(String flgTipoTrasferimento) {
		this.flgTipoTrasferimento = flgTipoTrasferimento;
	}
	public void setFlgValidTrasferimento(String flgValidTrasferimento) {
		this.flgValidTrasferimento = flgValidTrasferimento;
	}
	public void setFkVivaioUtente(Integer fkVivaioUtente) {
		this.fkVivaioUtente = fkVivaioUtente;
	}
	public void setFkConfUtente(Integer fkConfUtente) {
		this.fkConfUtente = fkConfUtente;
	}

}
