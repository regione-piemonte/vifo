/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class PartitaSemi {

	private Integer idInventarioSemi;
	private Integer fkInventarioFrutti;
	private Integer idProvenienza;
	private String certificatoType;
	private String codiceCertificato;
	private Double qtaCertificato;
	private Integer fkSpecie;
	private Date dataCertificato;
	private String provenienza;
	private Integer fkCategMateriale;
	private String produttore;
	private Date dataCarico;
	private Double qtaScaricoFruttiKg;
	private Double qtaCaricoKg;
	private Double qtaScaricoVenditaKg;
	private Double qtaDistruzioneKg;
	private Boolean isPianteFather;
	
	public Integer getIdInventarioSemi() {
		return idInventarioSemi;
	}
	public Integer getFkInventarioFrutti() {
		return fkInventarioFrutti;
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
	public Double getQtaCertificato() {
		return qtaCertificato;
	}
	public void setQtaCertificato(Double qtaCertificato) {
		this.qtaCertificato = qtaCertificato;
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
	public Double getQtaScaricoFruttiKg() {
		return qtaScaricoFruttiKg;
	}
	public Double getQtaCaricoKg() {
		return qtaCaricoKg;
	}
	public Double getQtaScaricoVenditaKg() {
		return qtaScaricoVenditaKg;
	}
	public Double getQtaDistruzioneKg() {
		return qtaDistruzioneKg;
	}
	public Boolean getIsPianteFather() {
		return isPianteFather;
	}
	public void setIdInventarioSemi(Integer idInventarioSemi) {
		this.idInventarioSemi = idInventarioSemi;
	}
	public void setFkInventarioFrutti(Integer fkInventarioFrutti) {
		this.fkInventarioFrutti = fkInventarioFrutti;
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
	public void setQtaScaricoFruttiKg(Double qtaScaricoFruttiKg) {
		this.qtaScaricoFruttiKg = qtaScaricoFruttiKg;
	}
	public void setQtaCaricoKg(Double qtaCaricoKg) {
		this.qtaCaricoKg = qtaCaricoKg;
	}
	public void setQtaScaricoVenditaKg(Double qtaScaricoVenditaKg) {
		this.qtaScaricoVenditaKg = qtaScaricoVenditaKg;
	}
	public void setQtaDistruzioneKg(Double qtaDistruzioneKg) {
		this.qtaDistruzioneKg = qtaDistruzioneKg;
	}
	public void setIsPianteFather(Boolean isPianteFather) {
		this.isPianteFather = isPianteFather;
	}
	
	
}