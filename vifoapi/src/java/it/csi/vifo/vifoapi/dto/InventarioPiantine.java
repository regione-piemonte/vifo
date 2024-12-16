/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class InventarioPiantine {

	private Integer idInventarioPiante;
	private Integer fkInventarioPiante; 
	private Integer fkInventarioSemi;
	private Double qtaScaricoSemiKg; 
	private String eta; 
	private String altezza; 
	private Integer fkTipoAllevamento; 
	private Integer annoRadicazioneSeme; 
	private Double prezzoUnitario; 
	private String posizionePartita; 
	private Integer qtaCarico; 
	private Integer qtaNonAssegnabile; 
	private Integer qtaDistruzione; 
	private String flgTipoTrasferimento; 
	private String motivoTrasferimento; 
	private String flgValidTrasf; 
	private Date dataValidTrasf; 
	private String noteValidTrasf; 
	private Integer fkConfigUtenteValidTrasf; 
	private Integer fkVivaio;
	
	public Integer getIdInventarioPiante() {
		return idInventarioPiante;
	}
	public Integer getFkInventarioPiante() {
		return fkInventarioPiante;
	}
	public Integer getFkInventarioSemi() {
		return fkInventarioSemi;
	}
	public Double getQtaScaricoSemiKg() {
		return qtaScaricoSemiKg;
	}
	public String getEta() {
		return eta;
	}
	public String getAltezza() {
		return altezza;
	}
	public Integer getFkTipoAllevamento() {
		return fkTipoAllevamento;
	}
	public Integer getAnnoRadicazioneSeme() {
		return annoRadicazioneSeme;
	}
	public Double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public String getPosizionePartita() {
		return posizionePartita;
	}
	public Integer getQtaCarico() {
		return qtaCarico;
	}
	public Integer getQtaNonAssegnabile() {
		return qtaNonAssegnabile;
	}
	public Integer getQtaDistruzione() {
		return qtaDistruzione;
	}
	public String getFlgTipoTrasferimento() {
		return flgTipoTrasferimento;
	}
	public String getMotivoTrasferimento() {
		return motivoTrasferimento;
	}
	public String getFlgValidTrasf() {
		return flgValidTrasf;
	}
	public Date getDataValidTrasf() {
		return dataValidTrasf;
	}
	public String getNoteValidTrasf() {
		return noteValidTrasf;
	}
	public Integer getFkConfigUtenteValidTrasf() {
		return fkConfigUtenteValidTrasf;
	}
	public Integer getFkVivaio() {
		return fkVivaio;
	}
	public void setIdInventarioPiante(Integer idInventarioPiante) {
		this.idInventarioPiante = idInventarioPiante;
	}
	public void setFkInventarioPiante(Integer fkInventarioPiante) {
		this.fkInventarioPiante = fkInventarioPiante;
	}
	public void setFkInventarioSemi(Integer fkInventarioSemi) {
		this.fkInventarioSemi = fkInventarioSemi;
	}
	public void setQtaScaricoSemiKg(Double qtaScaricoSemiKg) {
		this.qtaScaricoSemiKg = qtaScaricoSemiKg;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}
	public void setFkTipoAllevamento(Integer fkTipoAllevamento) {
		this.fkTipoAllevamento = fkTipoAllevamento;
	}
	public void setAnnoRadicazioneSeme(Integer annoRadicazioneSeme) {
		this.annoRadicazioneSeme = annoRadicazioneSeme;
	}
	public void setPrezzoUnitario(Double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public void setPosizionePartita(String posizionePartita) {
		this.posizionePartita = posizionePartita;
	}
	public void setQtaCarico(Integer qtaCarico) {
		this.qtaCarico = qtaCarico;
	}
	public void setQtaNonAssegnabile(Integer qtaNonAssegnabile) {
		this.qtaNonAssegnabile = qtaNonAssegnabile;
	}
	public void setQtaDistruzione(Integer qtaDistruzione) {
		this.qtaDistruzione = qtaDistruzione;
	}
	public void setFlgTipoTrasferimento(String flgTipoTrasferimento) {
		this.flgTipoTrasferimento = flgTipoTrasferimento;
	}
	public void setMotivoTrasferimento(String motivoTrasferimento) {
		this.motivoTrasferimento = motivoTrasferimento;
	}
	public void setFlgValidTrasf(String flgValidTrasf) {
		this.flgValidTrasf = flgValidTrasf;
	}
	public void setDataValidTrasf(Date dataValidTrasf) {
		this.dataValidTrasf = dataValidTrasf;
	}
	public void setNoteValidTrasf(String noteValidTrasf) {
		this.noteValidTrasf = noteValidTrasf;
	}
	public void setFkConfigUtenteValidTrasf(Integer fkConfigUtenteValidTrasf) {
		this.fkConfigUtenteValidTrasf = fkConfigUtenteValidTrasf;
	}
	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}
}
