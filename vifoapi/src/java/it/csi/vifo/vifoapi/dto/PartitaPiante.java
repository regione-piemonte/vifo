/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class PartitaPiante {
	
	private Integer idInventarioPiante;
	private Integer fkInventarioPiante;
	private Integer fkInventarioSemi;
	private Integer fkVivaio;
	private Integer idProvenienza;
	private String certificatoType;
	private String codiceCertificato;
	private Integer fkSpecie;
	private Date dataCertificato;
	private String provenienza;
	private Integer fkCategMateriale;
	private String produttore;
	private Date dataCarico;
	private Integer fkTipoAllevamento;
	private String eta;
	private String altezza;
	private Integer annoRadicazioneSeme;
	private Double prezzoUnitario;
	private Double qtaScaricoSemiKg;
	private Integer qtaNonAssegnabile;
	private Integer qtaCarico;
	private Integer qtaDistruzione;
	private String posizionePartita;
	private Boolean isPianteFather;
	private Integer numAssegnazioni;
	private String flgTipoTrasferimento;
	
	public Integer getIdInventarioPiante() {
		return idInventarioPiante;
	}
	public Integer getFkInventarioPiante() {
		return fkInventarioPiante;
	}
	public Integer getFkInventarioSemi() {
		return fkInventarioSemi;
	}
	public Integer getFkVivaio() {
		return fkVivaio;
	}
	public Integer getIdProvenienza() {
		return idProvenienza;
	}
	public String getCertificatoType() {
		return certificatoType;
	}
	public String getCodiceCertificato() {
		return codiceCertificato;
	}
	public Integer getFkSpecie() {
		return fkSpecie;
	}
	public Date getDataCertificato() {
		return dataCertificato;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public Integer getFkCategMateriale() {
		return fkCategMateriale;
	}
	public String getProduttore() {
		return produttore;
	}
	public Date getDataCarico() {
		return dataCarico;
	}
	public Integer getFkTipoAllevamento() {
		return fkTipoAllevamento;
	}
	public String getEta() {
		return eta;
	}
	public String getAltezza() {
		return altezza;
	}
	public Integer getAnnoRadicazioneSeme() {
		return annoRadicazioneSeme;
	}
	public Double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public Double getQtaScaricoSemiKg() {
		return qtaScaricoSemiKg;
	}
	public Integer getQtaNonAssegnabile() {
		return qtaNonAssegnabile;
	}
	public Integer getQtaCarico() {
		return qtaCarico;
	}
	public Integer getQtaDistruzione() {
		return qtaDistruzione;
	}
	public String getPosizionePartita() {
		return posizionePartita;
	}
	public Boolean getIsPianteFather() {
		return isPianteFather;
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
	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}
	public void setIdProvenienza(Integer idProvenienza) {
		this.idProvenienza = idProvenienza;
	}
	public void setCertificatoType(String certificatoType) {
		this.certificatoType = certificatoType;
	}
	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}
	public void setFkSpecie(Integer fkSpecie) {
		this.fkSpecie = fkSpecie;
	}
	public void setDataCertificato(Date dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setFkCategMateriale(Integer fkCategMateriale) {
		this.fkCategMateriale = fkCategMateriale;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
	}
	public void setFkTipoAllevamento(Integer fkTipoAllevamento) {
		this.fkTipoAllevamento = fkTipoAllevamento;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}
	public void setAnnoRadicazioneSeme(Integer annoRadicazioneSeme) {
		this.annoRadicazioneSeme = annoRadicazioneSeme;
	}
	public void setPrezzoUnitario(Double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public void setQtaScaricoSemiKg(Double qtaScaricoSemiKg) {
		this.qtaScaricoSemiKg = qtaScaricoSemiKg;
	}
	public void setQtaNonAssegnabile(Integer qtaNonAssegnabile) {
		this.qtaNonAssegnabile = qtaNonAssegnabile;
	}
	public void setQtaCarico(Integer qtaCarico) {
		this.qtaCarico = qtaCarico;
	}
	public void setQtaDistruzione(Integer qtaDistruzione) {
		this.qtaDistruzione = qtaDistruzione;
	}
	public void setPosizionePartita(String posizionePartita) {
		this.posizionePartita = posizionePartita;
	}
	public void setIsPianteFather(Boolean isPianteFather) {
		this.isPianteFather = isPianteFather;
	}
	public Integer getNumAssegnazioni() {
		return numAssegnazioni;
	}
	public void setNumAssegnazioni(Integer numAssegnazioni) {
		this.numAssegnazioni = numAssegnazioni;
	}
	public String getFlgTipoTrasferimento() {
		return flgTipoTrasferimento;
	}
	public void setFlgTipoTrasferimento(String flgTipoTrasferimento) {
		this.flgTipoTrasferimento = flgTipoTrasferimento;
	}
	
}
