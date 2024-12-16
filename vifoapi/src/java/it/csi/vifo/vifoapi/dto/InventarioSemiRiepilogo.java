/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InventarioSemiRiepilogo {

	private Integer idInventario;
	private Integer fkInventarioFrutti;
	private String certificatoProvenienza;
	private String specie;
	private String dataCertificato;
	private String provenienza;
	private String produttore;
	private String codCategoriaMateriale;
	private String descrCategoriaMaterialie;
	private String dataCarico;
	private Double qtaScaricoFruttiKg;
	private Double qtaCaricoKg;
	private Double qtaScaricoPiantineKg;
	private Double qtaScaricoVenditaKg;
	private Double qtaDistruzioneKg;
	private Double qtaResiduaKg;
	
	public Integer getIdInventario() {
		return idInventario;
	}
	public Integer getFkInventarioFrutti() {
		return fkInventarioFrutti;
	}
	public String getCertificatoProvenienza() {
		return certificatoProvenienza;
	}
	public String getSpecie() {
		return specie;
	}
	public String getDataCertificato() {
		return dataCertificato;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public String getProduttore() {
		return produttore;
	}
	public String getCodCategoriaMateriale() {
		return codCategoriaMateriale;
	}
	public String getDescrCategoriaMaterialie() {
		return descrCategoriaMaterialie;
	}
	public String getDataCarico() {
		return dataCarico;
	}
	public Double getQtaScaricoFruttiKg() {
		return qtaScaricoFruttiKg;
	}
	public Double getQtaCaricoKg() {
		return qtaCaricoKg;
	}
	public Double getQtaScaricoPiantineKg() {
		return qtaScaricoPiantineKg;
	}
	public Double getQtaScaricoVenditaKg() {
		return qtaScaricoVenditaKg;
	}
	public Double getQtaDistruzioneKg() {
		return qtaDistruzioneKg;
	}
	public Double getQtaResiduaKg() {
		return qtaResiduaKg;
	}
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}
	public void setFkInventarioFrutti(Integer fkInventarioFrutti) {
		this.fkInventarioFrutti = fkInventarioFrutti;
	}
	public void setCertificatoProvenienza(String certificatoProvenienza) {
		this.certificatoProvenienza = certificatoProvenienza;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public void setDataCertificato(String dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	public void setCodCategoriaMateriale(String codCategoriaMateriale) {
		this.codCategoriaMateriale = codCategoriaMateriale;
	}
	public void setDescrCategoriaMaterialie(String descrCategoriaMaterialie) {
		this.descrCategoriaMaterialie = descrCategoriaMaterialie;
	}
	public void setDataCarico(String dataCarico) {
		this.dataCarico = dataCarico;
	}
	public void setQtaScaricoFruttiKg(Double qtaScaricoFruttiKg) {
		this.qtaScaricoFruttiKg = qtaScaricoFruttiKg;
	}
	public void setQtaCaricoKg(Double qtaCaricoKg) {
		this.qtaCaricoKg = qtaCaricoKg;
	}
	public void setQtaScaricoPiantineKg(Double qtaScaricoPiantineKg) {
		this.qtaScaricoPiantineKg = qtaScaricoPiantineKg;
	}
	public void setQtaScaricoVenditaKg(Double qtaScaricoVenditaKg) {
		this.qtaScaricoVenditaKg = qtaScaricoVenditaKg;
	}
	public void setQtaDistruzioneKg(Double qtaDistruzioneKg) {
		this.qtaDistruzioneKg = qtaDistruzioneKg;
	}
	public void setQtaResiduaKg(Double qtaResiduaKg) {
		this.qtaResiduaKg = qtaResiduaKg;
	}
	
	
}
