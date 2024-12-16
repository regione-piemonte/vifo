/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InfoDomanda {
	
	private String dataInvio;
	private String protocolloInvio;
	private String noteInvio;
	private String notePreassegnata;
	private String dataAssegnazione;
	private TSoggetto operatoreAssegnazione;
	private String protocolloAssegnazione;	
	private String noteAssegnazione;
	private String dataRichVariazioni;
	private String noteRichVariazioni;
	private String dataAccettazione;
	private String protocolloAccettazione;	
	private String dataRitiro;
	private TSoggetto operatoreRitiro;
	private String dataChiusura;
	private TSoggetto operatoreChiusura;
	private String scopoAssegnGratuita;
	private String titolarita;
	private String dataRifiuto;
	private TSoggetto operatoreRifiuto;
	private String noteRifiuto;
	private String statoPagamento;
	
	public String getDataInvio() {
		return dataInvio;
	}
	public String getProtocolloInvio() {
		return protocolloInvio;
	}
	public String getNoteInvio() {
		return noteInvio;
	}
	public String getNotePreassegnata() {
		return notePreassegnata;
	}
	public String getDataAssegnazione() {
		return dataAssegnazione;
	}
	public TSoggetto getOperatoreAssegnazione() {
		return operatoreAssegnazione;
	}
	public String getProtocolloAssegnazione() {
		return protocolloAssegnazione;
	}
	public String getNoteAssegnazione() {
		return noteAssegnazione;
	}
	public String getDataRichVariazioni() {
		return dataRichVariazioni;
	}
	public String getNoteRichVariazioni() {
		return noteRichVariazioni;
	}
	public String getDataAccettazione() {
		return dataAccettazione;
	}
	public String getProtocolloAccettazione() {
		return protocolloAccettazione;
	}
	public String getDataRitiro() {
		return dataRitiro;
	}
	public TSoggetto getOperatoreRitiro() {
		return operatoreRitiro;
	}
	public String getDataChiusura() {
		return dataChiusura;
	}
	public TSoggetto getOperatoreChiusura() {
		return operatoreChiusura;
	}
	public String getScopoAssegnGratuita() {
		return scopoAssegnGratuita;
	}
	public String getTitolarita() {
		return titolarita;
	}
	public String getDataRifiuto() {
		return dataRifiuto;
	}
	public TSoggetto getOperatoreRifiuto() {
		return operatoreRifiuto;
	}
	public String getNoteRifiuto() {
		return noteRifiuto;
	}
	public void setDataInvio(String dataInvio) {
		this.dataInvio = dataInvio;
	}
	public void setProtocolloInvio(String protocolloInvio) {
		this.protocolloInvio = protocolloInvio;
	}
	public void setNoteInvio(String noteInvio) {
		this.noteInvio = noteInvio;
	}
	public void setNotePreassegnata(String notePreassegnata) {
		this.notePreassegnata = notePreassegnata;
	}
	public void setDataAssegnazione(String dataAssegnazione) {
		this.dataAssegnazione = dataAssegnazione;
	}
	public void setOperatoreAssegnazione(TSoggetto operatoreAssegnazione) {
		this.operatoreAssegnazione = operatoreAssegnazione;
	}
	public void setProtocolloAssegnazione(String protocolloAssegnazione) {
		this.protocolloAssegnazione = protocolloAssegnazione;
	}
	public void setNoteAssegnazione(String noteAssegnazione) {
		this.noteAssegnazione = noteAssegnazione;
	}
	public void setDataRichVariazioni(String dataRichVariazioni) {
		this.dataRichVariazioni = dataRichVariazioni;
	}
	public void setNoteRichVariazioni(String noteRichVariazioni) {
		this.noteRichVariazioni = noteRichVariazioni;
	}
	public void setDataAccettazione(String dataAccettazione) {
		this.dataAccettazione = dataAccettazione;
	}
	public void setProtocolloAccettazione(String protocolloAccettazione) {
		this.protocolloAccettazione = protocolloAccettazione;
	}
	public void setDataRitiro(String dataRitiro) {
		this.dataRitiro = dataRitiro;
	}
	public void setOperatoreRitiro(TSoggetto operatoreRitiro) {
		this.operatoreRitiro = operatoreRitiro;
	}
	public void setDataChiusura(String dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	public void setOperatoreChiusura(TSoggetto operatoreChiusura) {
		this.operatoreChiusura = operatoreChiusura;
	}
	public void setScopoAssegnGratuita(String scopoAssegnGratuita) {
		this.scopoAssegnGratuita = scopoAssegnGratuita;
	}
	public void setTitolarita(String titolarita) {
		this.titolarita = titolarita;
	}
	public void setDataRifiuto(String dataRifiuto) {
		this.dataRifiuto = dataRifiuto;
	}
	public void setOperatoreRifiuto(TSoggetto operatoreRifiuto) {
		this.operatoreRifiuto = operatoreRifiuto;
	}
	public void setNoteRifiuto(String noteRifiuto) {
		this.noteRifiuto = noteRifiuto;
	}
	public String getStatoPagamento() {
		return statoPagamento;
	}
	public void setStatoPagamento(String statoPagamento) {
		this.statoPagamento = statoPagamento;
	}
	
}
