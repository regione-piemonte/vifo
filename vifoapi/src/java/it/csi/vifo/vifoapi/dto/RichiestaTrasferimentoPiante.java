/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class RichiestaTrasferimentoPiante {
	private Integer idInventario;
	private Integer fkInventarioPiante;
	private Integer fkSpecie;	
	private String specieName;
	private Integer fkVivaioTo;
	private String vivaioToName;
	private Integer fkVivaioFrom;
	private String vivaioFromName;
	private String operatoreName;
	private String dataInvioNotifica;
	private Integer qtaCarico;
	private String motivoRichiesta;
	private Boolean isTrasferimento;
	private String flgValid;
	private Integer fkConfigUtenteValidTrasf;
	private String noteValidInvalidTrasf;
	
	public Integer getIdInventario() {
		return idInventario;
	}
	public Integer getFkInventarioPiante() {
		return fkInventarioPiante;
	}
	public Integer getFkSpecie() {
		return fkSpecie;
	}
	public String getSpecieName() {
		return specieName;
	}
	public Integer getFkVivaioTo() {
		return fkVivaioTo;
	}
	public String getVivaioToName() {
		return vivaioToName;
	}
	public Integer getFkVivaioFrom() {
		return fkVivaioFrom;
	}
	public String getVivaioFromName() {
		return vivaioFromName;
	}
	public String getOperatoreName() {
		return operatoreName;
	}
	public String getDataInvioNotifica() {
		return dataInvioNotifica;
	}
	public Integer getQtaCarico() {
		return qtaCarico;
	}
	public String getMotivoRichiesta() {
		return motivoRichiesta;
	}
	public Boolean getIsTrasferimento() {
		return isTrasferimento;
	}
	public String getFlgValid() {
		return flgValid;
	}
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}
	public void setFkInventarioPiante(Integer fkInventarioPiante) {
		this.fkInventarioPiante = fkInventarioPiante;
	}
	public void setFkSpecie(Integer fkSpecie) {
		this.fkSpecie = fkSpecie;
	}
	public void setSpecieName(String specieName) {
		this.specieName = specieName;
	}
	public void setFkVivaioTo(Integer fkVivaioTo) {
		this.fkVivaioTo = fkVivaioTo;
	}
	public void setVivaioToName(String vivaioToName) {
		this.vivaioToName = vivaioToName;
	}
	public void setFkVivaioFrom(Integer fkVivaioFrom) {
		this.fkVivaioFrom = fkVivaioFrom;
	}
	public void setVivaioFromName(String vivaioFromName) {
		this.vivaioFromName = vivaioFromName;
	}
	public void setOperatoreName(String operatoreName) {
		this.operatoreName = operatoreName;
	}
	public void setDataInvioNotifica(String dataInvioNotifica) {
		this.dataInvioNotifica = dataInvioNotifica;
	}
	public void setQtaCarico(Integer qtaCarico) {
		this.qtaCarico = qtaCarico;
	}
	public void setMotivoRichiesta(String motivoRichiesta) {
		this.motivoRichiesta = motivoRichiesta;
	}
	public void setIsTrasferimento(Boolean isTrasferimento) {
		this.isTrasferimento = isTrasferimento;
	}
	public void setFlgValid(String flgValid) {
		this.flgValid = flgValid;
	}
	public Integer getFkConfigUtenteValidTrasf() {
		return fkConfigUtenteValidTrasf;
	}
	public void setFkConfigUtenteValidTrasf(Integer fkConfigUtenteValidTrasf) {
		this.fkConfigUtenteValidTrasf = fkConfigUtenteValidTrasf;
	}
	public String getNoteValidInvalidTrasf() {
		return noteValidInvalidTrasf;
	}
	public void setNoteValidInvalidTrasf(String noteValidInvalidTrasf) {
		this.noteValidInvalidTrasf = noteValidInvalidTrasf;
	}
	
	
}
