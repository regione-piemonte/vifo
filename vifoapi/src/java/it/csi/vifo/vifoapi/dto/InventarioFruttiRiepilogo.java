/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InventarioFruttiRiepilogo {
	private Integer idInventarioFrutti;
	private String certificatoProvenienza;
	private String dataCertificato;
	private boolean flgNoFiniForestali;
	private boolean flgSpecie386;
	private String specie;
	private String provenienza;
	private String produttore;
	private String codCategoriaMateriale;
	private String descrCategoriaMaterialie;
	private String dataCarico;
	private Double qtaCaricoKg;
	private Double qtaScaricoSemiKg;
	private Double qtaScaricoVenditaKg;
	private Double qtaDistruzioneKg;
	private Double qtaResidua;
	
	public Integer getIdInventarioFrutti() {
		return idInventarioFrutti;
	}
	public String getCertificatoProvenienza() {
		return certificatoProvenienza;
	}
	public String getDataCertificato() {
		return dataCertificato;
	}
	public boolean isFlgNoFiniForestali() {
		return flgNoFiniForestali;
	}
	public boolean isFlgSpecie386() {
		return flgSpecie386;
	}
	public String getSpecie() {
		return specie;
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
	public Double getQtaCaricoKg() {
		return qtaCaricoKg;
	}
	public Double getQtaScaricoSemiKg() {
		return qtaScaricoSemiKg;
	}
	public Double getQtaScaricoVenditaKg() {
		return qtaScaricoVenditaKg;
	}
	public Double getQtaDistruzioneKg() {
		return qtaDistruzioneKg;
	}
	public Double getQtaResidua() {
		return qtaResidua;
	}
	public void setIdInventarioFrutti(Integer idInventarioFrutti) {
		this.idInventarioFrutti = idInventarioFrutti;
	}
	public void setCertificatoProvenienza(String certificatoProvenienza) {
		this.certificatoProvenienza = certificatoProvenienza;
	}
	public void setDataCertificato(String dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setFlgNoFiniForestali(boolean flgNoFiniForestali) {
		this.flgNoFiniForestali = flgNoFiniForestali;
	}
	public void setFlgSpecie386(boolean flgSpecie386) {
		this.flgSpecie386 = flgSpecie386;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
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
	public void setQtaCaricoKg(Double qtaCaricoKg) {
		this.qtaCaricoKg = qtaCaricoKg;
	}
	public void setQtaScaricoSemiKg(Double qtaScaricoSemiKg) {
		this.qtaScaricoSemiKg = qtaScaricoSemiKg;
	}
	public void setQtaScaricoVenditaKg(Double qtaScaricoVenditaKg) {
		this.qtaScaricoVenditaKg = qtaScaricoVenditaKg;
	}
	public void setQtaDistruzioneKg(Double qtaDistruzioneKg) {
		this.qtaDistruzioneKg = qtaDistruzioneKg;
	}
	public void setQtaResidua(Double qtaResidua) {
		this.qtaResidua = qtaResidua;
	}
	
}
