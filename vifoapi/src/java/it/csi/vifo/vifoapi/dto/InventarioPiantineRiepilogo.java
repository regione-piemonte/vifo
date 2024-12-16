/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class InventarioPiantineRiepilogo {

	private Integer idInventarioPiante;
	private Integer fkInventarioPiante;
	private Integer fkInventarioSemi;
	private String certificatoProvenienza;
	private String dataCertificato;
	private Byte flgNoFiniForestali;
	private Byte flgSpecie386;
	private String specie;
	private String provenienza;
	private String produttore;
	private String codCategMateriale;
	private String descrCategMateriale;
	private String descrTipoAllevamento;
	private String eta;
	private String altezza;
	private Integer annoRadicazioneSeme;
	private Double prezzoUnitario;
	private Double qtaScaricoSemiKg;
	private Date dataCarico;
	private Integer qtaCarico;
	private Integer qtaNonAssegnabile;
	private Integer qtaScaricoAltrePiante;
	private Integer qtaAssegnabileTeorica;
	private Integer qtaAssegnata;
	private Integer qtaRitirata;
	private Integer qtaDistruzione;
	private Integer qtaDisponibile;
	private String posizione;
	
	public Integer getIdInventarioPiante() {
		return idInventarioPiante;
	}
	public Integer getFkInventarioPiante() {
		return fkInventarioPiante;
	}
	public Integer getFkInventarioSemi() {
		return fkInventarioSemi;
	}
	public String getCertificatoProvenienza() {
		return certificatoProvenienza;
	}
	public String getDataCertificato() {
		return dataCertificato;
	}
	public Byte getFlgNoFiniForestali() {
		return flgNoFiniForestali;
	}
	public Byte getFlgSpecie386() {
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
	public String getCodCategMateriale() {
		return codCategMateriale;
	}
	public String getDescrCategMateriale() {
		return descrCategMateriale;
	}
	public String getDescrTipoAllevamento() {
		return descrTipoAllevamento;
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
	public Date getDataCarico() {
		return dataCarico;
	}
	public Integer getQtaCarico() {
		return qtaCarico;
	}
	public Integer getQtaNonAssegnabile() {
		return qtaNonAssegnabile;
	}
	public Integer getQtaScaricoAltrePiante() {
		return qtaScaricoAltrePiante;
	}
	public Integer getQtaAssegnabileTeorica() {
		return qtaAssegnabileTeorica;
	}
	public Integer getQtaAssegnata() {
		return qtaAssegnata;
	}
	public Integer getQtaRitirata() {
		return qtaRitirata;
	}
	public Integer getQtaDistruzione() {
		return qtaDistruzione;
	}
	public Integer getQtaDisponibile() {
		return qtaDisponibile;
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
	public void setCertificatoProvenienza(String certificatoProvenienza) {
		this.certificatoProvenienza = certificatoProvenienza;
	}
	public void setDataCertificato(String dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setFlgNoFiniForestali(Byte flgNoFiniForestali) {
		this.flgNoFiniForestali = flgNoFiniForestali;
	}
	public void setFlgSpecie386(Byte flgSpecie386) {
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
	public void setCodCategMateriale(String codCategMateriale) {
		this.codCategMateriale = codCategMateriale;
	}
	public void setDescrCategMateriale(String descrCategMateriale) {
		this.descrCategMateriale = descrCategMateriale;
	}
	public void setDescrTipoAllevamento(String descrTipoAllevamento) {
		this.descrTipoAllevamento = descrTipoAllevamento;
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
	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
	}
	public void setQtaCarico(Integer qtaCarico) {
		this.qtaCarico = qtaCarico;
	}
	public void setQtaNonAssegnabile(Integer qtaNonAssegnabile) {
		this.qtaNonAssegnabile = qtaNonAssegnabile;
	}
	public void setQtaScaricoAltrePiante(Integer qtaScaricoAltrePiante) {
		this.qtaScaricoAltrePiante = qtaScaricoAltrePiante;
	}
	public void setQtaAssegnabileTeorica(Integer qtaAssegnabileTeorica) {
		this.qtaAssegnabileTeorica = qtaAssegnabileTeorica;
	}
	public void setQtaAssegnata(Integer qtaAssegnata) {
		this.qtaAssegnata = qtaAssegnata;
	}
	public void setQtaRitirata(Integer qtaRitirata) {
		this.qtaRitirata = qtaRitirata;
	}
	public void setQtaDistruzione(Integer qtaDistruzione) {
		this.qtaDistruzione = qtaDistruzione;
	}
	public void setQtaDisponibile(Integer qtaDisponibile) {
		this.qtaDisponibile = qtaDisponibile;
	}
	public String getPosizione() {
		return posizione;
	}
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	
	
}
