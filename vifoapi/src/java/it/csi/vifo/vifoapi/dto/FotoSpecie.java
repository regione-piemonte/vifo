/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class FotoSpecie {
	private Integer idFotoSpecie;
	private Integer fkTipoFoto;
	private Integer fkSpecie;
	private String nomeFile;
	private String estensioneFile;
	private Long dimensioneFileKb;
	private Date dataUpload;
	private String uidIndex;
	private String note;
	private Integer fkConfigUtente;
	public Integer getIdFotoSpecie() {
		return idFotoSpecie;
	}
	public Integer getFkTipoFoto() {
		return fkTipoFoto;
	}
	public Integer getFkSpecie() {
		return fkSpecie;
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
	public String getUidIndex() {
		return uidIndex;
	}
	public String getNote() {
		return note;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdFotoSpecie(Integer idFotoSpecie) {
		this.idFotoSpecie = idFotoSpecie;
	}
	public void setFkTipoFoto(Integer fkTipoFoto) {
		this.fkTipoFoto = fkTipoFoto;
	}
	public void setFkSpecie(Integer fkSpecie) {
		this.fkSpecie = fkSpecie;
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
	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	
	
}
