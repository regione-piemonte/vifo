/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class DomandaRiepilogo {
	
	private Integer idDomanda;
	private String dataInvio;
	private Integer tipoDomanda;
	private String richiedente;
	private String Vivaio;
	private String stato;
	private String statoPagamento;
	private boolean isVivaioCompilatore;
	private boolean isEntePubblico;
	
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public String getDataInvio() {
		return dataInvio;
	}
	public Integer getTipoDomanda() {
		return tipoDomanda;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public String getVivaio() {
		return Vivaio;
	}
	public String getStato() {
		return stato;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setDataInvio(String dataInvio) {
		this.dataInvio = dataInvio;
	}
	public void setTipoDomanda(Integer tipoDomanda) {
		this.tipoDomanda = tipoDomanda;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public void setVivaio(String Vivaio) {
		this.Vivaio = Vivaio;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public boolean isVivaioCompilatore() {
		return isVivaioCompilatore;
	}
	public void setVivaioCompilatore(boolean isVivaioCompilatore) {
		this.isVivaioCompilatore = isVivaioCompilatore;
	}
	public String getStatoPagamento() {
		return statoPagamento;
	}
	public void setStatoPagamento(String statoPagamento) {
		this.statoPagamento = statoPagamento;
	}
	public boolean isEntePubblico() {
		return isEntePubblico;
	}
	public void setEntePubblico(boolean isEntePubblico) {
		this.isEntePubblico = isEntePubblico;
	}
	
}
