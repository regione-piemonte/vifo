/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class DocumentoAllegato {

	private Integer idAllegato;
	private Integer fkDomanda;
	private Integer fkTipoAllegato;
	private String tipoAllegatoDescr;
	private String nomeFile;
	private String estensioneFile;	
	private Long dimensioneFileKb;	
	private Date dataUpload;
	private String note;
	private Integer fkConfigUtente;
	private String uidIndex;
	private String idDocDoqui;
	private String idClassificazioneDoqui;
	
	public Integer getIdAllegato() {
		return idAllegato;
	}
	public Integer getFkDomanda() {
		return fkDomanda;
	}
	public Integer getFkTipoAllegato() {
		return fkTipoAllegato;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public String getEstensioneFile() {
		return estensioneFile;
	}
	public Long getDimensioneFileKb() {
		return dimensioneFileKb;
	}
	public Date getDataUpload() {
		return dataUpload;
	}
	public String getNote() {
		return note;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public String getUidIndex() {
		return uidIndex;
	}
	public String getIdDocDoqui() {
		return idDocDoqui;
	}
	public String getIdClassificazioneDoqui() {
		return idClassificazioneDoqui;
	}
	public void setIdAllegato(Integer idAllegato) {
		this.idAllegato = idAllegato;
	}
	public void setFkDomanda(Integer fkDomanda) {
		this.fkDomanda = fkDomanda;
	}
	public void setFkTipoAllegato(Integer fkTipoAllegato) {
		this.fkTipoAllegato = fkTipoAllegato;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public void setEstensioneFile(String estensioneFile) {
		this.estensioneFile = estensioneFile;
	}
	public void setDimensioneFileKb(Long dimensioneFileKb) {
		this.dimensioneFileKb = dimensioneFileKb;
	}
	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}
	public void setIdDocDoqui(String idDocDoqui) {
		this.idDocDoqui = idDocDoqui;
	}
	public void setIdClassificazioneDoqui(String idClassificazioneDoqui) {
		this.idClassificazioneDoqui = idClassificazioneDoqui;
	}
	public String getTipoAllegatoDescr() {
		return tipoAllegatoDescr;
	}
	public void setTipoAllegatoDescr(String tipoAllegatoDescr) {
		this.tipoAllegatoDescr = tipoAllegatoDescr;
	}
	
	
	
}
