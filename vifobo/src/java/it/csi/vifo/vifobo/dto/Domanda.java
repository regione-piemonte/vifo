/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

import java.util.Date;
import java.util.List;

public class Domanda {
	
	private Integer idDomanda;
	private Integer fkSoggetto;
	private Integer fkTipoDomanda;
	private Integer fkTitolarita;
	private Integer fkScopo;
	private Integer fkVivaio;
	private Integer fkConfigUtente;
	private String note;
	private String numMarcaBollo;
	private Double totImportiEuro;
	private Double scontoApplicato;
	private Double totImponibileEuro;
	private Double aliquotaIva;
	private Double importoIva;
	private Double totFattura;
	private Date dataInserimento;
	private Date dataAggiornamento;
	private int fkStatoDomanda;
	private String statoDomanda;
	private Destinazione[] listDestinazione;
	private SpecieRichiesta[] listSpecie;
	private List<DocumentoAllegato> listDocumenti;
	private TSoggetto soggetto;
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public Integer getFkSoggetto() {
		return fkSoggetto;
	}
	public Integer getFkTipoDomanda() {
		return fkTipoDomanda;
	}
	public Integer getFkTitolarita() {
		return fkTitolarita;
	}
	public Integer getFkScopo() {
		return fkScopo;
	}
	public Integer getFkVivaio() {
		return fkVivaio;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public String getNote() {
		return note;
	}
	public String getNumMarcaBollo() {
		return numMarcaBollo;
	}
	public Double getTotImportiEuro() {
		return totImportiEuro;
	}
	public Double getScontoApplicato() {
		return scontoApplicato;
	}
	public Double getTotImponibileEuro() {
		return totImponibileEuro;
	}
	public Double getAliquotaIva() {
		return aliquotaIva;
	}
	public Double getImportoIva() {
		return importoIva;
	}
	public Double getTotFattura() {
		return totFattura;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public int getFkStatoDomanda() {
		return fkStatoDomanda;
	}
	public String getStatoDomanda() {
		return statoDomanda;
	}
	public Destinazione[] getListDestinazione() {
		return listDestinazione;
	}
	public SpecieRichiesta[] getListSpecie() {
		return listSpecie;
	}
	public List<DocumentoAllegato> getListDocumenti() {
		return listDocumenti;
	}
	public TSoggetto getSoggetto() {
		return soggetto;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}
	public void setFkTipoDomanda(Integer fkTipoDomanda) {
		this.fkTipoDomanda = fkTipoDomanda;
	}
	public void setFkTitolarita(Integer fkTitolarita) {
		this.fkTitolarita = fkTitolarita;
	}
	public void setFkScopo(Integer fkScopo) {
		this.fkScopo = fkScopo;
	}
	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setNumMarcaBollo(String numMarcaBollo) {
		this.numMarcaBollo = numMarcaBollo;
	}
	public void setTotImportiEuro(Double totImportiEuro) {
		this.totImportiEuro = totImportiEuro;
	}
	public void setScontoApplicato(Double scontoApplicato) {
		this.scontoApplicato = scontoApplicato;
	}
	public void setTotImponibileEuro(Double totImponibileEuro) {
		this.totImponibileEuro = totImponibileEuro;
	}
	public void setAliquotaIva(Double aliquotaIva) {
		this.aliquotaIva = aliquotaIva;
	}
	public void setImportoIva(Double importoIva) {
		this.importoIva = importoIva;
	}
	public void setTotFattura(Double totFattura) {
		this.totFattura = totFattura;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public void setFkStatoDomanda(int fkStatoDomanda) {
		this.fkStatoDomanda = fkStatoDomanda;
	}
	public void setStatoDomanda(String statoDomanda) {
		this.statoDomanda = statoDomanda;
	}
	public void setListDestinazione(Destinazione[] listDestinazione) {
		this.listDestinazione = listDestinazione;
	}
	public void setListSpecie(SpecieRichiesta[] listSpecie) {
		this.listSpecie = listSpecie;
	}
	public void setListDocumenti(List<DocumentoAllegato> listDocumenti) {
		this.listDocumenti = listDocumenti;
	}
	public void setSoggetto(TSoggetto soggetto) {
		this.soggetto = soggetto;
	}
	
	
}
