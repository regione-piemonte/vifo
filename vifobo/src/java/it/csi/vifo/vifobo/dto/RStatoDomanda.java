/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

import java.util.Date;

public class RStatoDomanda {
	private Integer idStatoDomanda;
	private Integer idDomanda;
	private Integer progressivo;
	private String numeroProtocollo;
	private Date dataProtocollo;
	private String noteStato;
	private Date dataInserimento;
	private Date dataFineStato;
	private Integer fkConfigUtente;
	
	
	public Integer getIdStatoDomanda() {
		return idStatoDomanda;
	}
	public void setIdStatoDomanda(Integer idStatoDomanda) {
		this.idStatoDomanda = idStatoDomanda;
	}
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public Integer getProgressivo() {
		return progressivo;
	}
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public Date getDataProtocollo() {
		return dataProtocollo;
	}
	public String getNoteStato() {
		return noteStato;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataFineStato() {
		return dataFineStato;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public void setNoteStato(String noteStato) {
		this.noteStato = noteStato;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataFineStato(Date dataFineStato) {
		this.dataFineStato = dataFineStato;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	
	
}
