/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

import it.csi.vifo.vifoapi.util.CodicePagamentoEnum;

public class Pagamento {
	private int idPagamento;
	private int fkDomanda;
	private int fkTipoPagamento;
	private byte flgFattura;
	private String iuv;
	private String esito;
	private CodicePagamentoEnum statoPagamento;
	private Date dataInserimento;
	private Date dataAggiornamento;
	private int fkConfigUtente;
	
	public int getIdPagamento() {
		return idPagamento;
	}
	public int getFkDomanda() {
		return fkDomanda;
	}
	public int getFkTipoPagamento() {
		return fkTipoPagamento;
	}
	public byte getFlgFattura() {
		return flgFattura;
	}
	public String getIuv() {
		return iuv;
	}
	public String getEsito() {
		return esito;
	}
	public CodicePagamentoEnum getStatoPagamento() {
		return statoPagamento;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public int getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public void setFkDomanda(int fkDomanda) {
		this.fkDomanda = fkDomanda;
	}
	public void setFkTipoPagamento(int fkTipoPagamento) {
		this.fkTipoPagamento = fkTipoPagamento;
	}
	public void setFlgFattura(byte flgFattura) {
		this.flgFattura = flgFattura;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public void setStatoPagamento(CodicePagamentoEnum statoPagamento) {
		this.statoPagamento = statoPagamento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public void setFkConfigUtente(int fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	
	
	
}
