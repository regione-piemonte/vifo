/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

import java.util.Date;

public class BoProcLog {
	private Integer idCnfBoprocLog;
	private String fkPratica;
	private Date dataInizio;
	private Date dataFine;
	private Integer fkStepBoproc;
	private String esito;
	private Integer countTentativo;
	private String codErroreBoproc;
	private String noteErroreBoproc;
	private String jsonBoproc;
	
	public Integer getIdCnfBoprocLog() {
		return idCnfBoprocLog;
	}
	public void setIdCnfBoprocLog(Integer idCnfBoprocLog) {
		this.idCnfBoprocLog = idCnfBoprocLog;
	}
	public String getFkPratica() {
		return fkPratica;
	}
	public void setFkPratica(String fkPratica) {
		this.fkPratica = fkPratica;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Integer getFkStepBoproc() {
		return fkStepBoproc;
	}
	public void setFkStepBoproc(Integer fkStepBoproc) {
		this.fkStepBoproc = fkStepBoproc;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public Integer getCountTentativo() {
		return countTentativo;
	}
	public void setCountTentativo(Integer countTentativo) {
		this.countTentativo = countTentativo;
	}
	public String getCodErroreBoproc() {
		return codErroreBoproc;
	}
	public void setCodErroreBoproc(String codErroreBoproc) {
		this.codErroreBoproc = codErroreBoproc;
	}
	public String getNoteErroreBoproc() {
		return noteErroreBoproc;
	}
	public void setNoteErroreBoproc(String noteErroreBoproc) {
		this.noteErroreBoproc = noteErroreBoproc;
	}
	public String getJsonBoproc() {
		return jsonBoproc;
	}
	public void setJsonBoproc(String jsonBoproc) {
		this.jsonBoproc = jsonBoproc;
	}
	
	
	}
