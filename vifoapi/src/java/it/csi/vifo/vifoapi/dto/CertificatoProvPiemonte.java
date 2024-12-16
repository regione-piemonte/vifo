/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class CertificatoProvPiemonte {

	Integer idProvenienza;
	String numeroCertificato;
	Byte flgTipoProduzione;
	Integer fkNaturaMateriale;
	Integer fkSpecie;
	Integer fkCategMateriale;
	Integer fkTipoMateriale;
	Integer destinazione;
	Integer fkPopolamento;
	String miscuglio;
	Integer fkOrigine;
	String descrizioneOrigine;
	String provenienza;
	String produttore;
	String altitudine;
	Integer annoMaturazioneSemi;
	Double qtaMaterialeBase;
	Integer fkUdmMateriale;
	String flgPartitaPrecedente;
	String nCertificatoPrecedente;
	Double qtaPartitaIniziale;
	Double qtaResidua;
	String durataAllevamento;
	String flgPropagazione;
	String metodoPropagazione;
	String nCicliPropagazione;
	Date dataCertificato;
	String note;
	String origineNonAutoctoni;
	
	public Integer getIdProvenienza() {
		return idProvenienza;
	}
	public String getNumeroCertificato() {
		return numeroCertificato;
	}
	public Byte getFlgTipoProduzione() {
		return flgTipoProduzione;
	}
	public Integer getFkNaturaMateriale() {
		return fkNaturaMateriale;
	}
	public Integer getFkSpecie() {
		return fkSpecie;
	}
	public Integer getFkCategMateriale() {
		return fkCategMateriale;
	}
	public Integer getFkTipoMateriale() {
		return fkTipoMateriale;
	}
	public Integer getDestinazione() {
		return destinazione;
	}
	public Integer getFkPopolamento() {
		return fkPopolamento;
	}
	public String getMiscuglio() {
		return miscuglio;
	}
	public Integer getFkOrigine() {
		return fkOrigine;
	}
	public String getDescrizioneOrigine() {
		return descrizioneOrigine;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public String getProduttore() {
		return produttore;
	}
	public String getAltitudine() {
		return altitudine;
	}
	public Integer getAnnoMaturazioneSemi() {
		return annoMaturazioneSemi;
	}
	public Double getQtaMaterialeBase() {
		return qtaMaterialeBase;
	}
	public Integer getFkUdmMateriale() {
		return fkUdmMateriale;
	}
	public String getFlgPartitaPrecedente() {
		return flgPartitaPrecedente;
	}
	public String getnCertificatoPrecedente() {
		return nCertificatoPrecedente;
	}
	public Double getQtaPartitaIniziale() {
		return qtaPartitaIniziale;
	}	
	public Double getQtaResidua() {
		return qtaResidua;
	}
	public void setQtaResidua(Double qtaResidua) {
		this.qtaResidua = qtaResidua;
	}
	public String getDurataAllevamento() {
		return durataAllevamento;
	}
	public String getFlgPropagazione() {
		return flgPropagazione;
	}
	public String getMetodoPropagazione() {
		return metodoPropagazione;
	}
	public String getnCicliPropagazione() {
		return nCicliPropagazione;
	}
	public Date getDataCertificato() {
		return dataCertificato;
	}
	public String getNote() {
		return note;
	}
	public void setIdProvenienza(Integer idProvenienza) {
		this.idProvenienza = idProvenienza;
	}
	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = numeroCertificato;
	}
	public void setFlgTipoProduzione(Byte flgTipoProduzione) {
		this.flgTipoProduzione = flgTipoProduzione;
	}
	public void setFkNaturaMateriale(Integer fkNaturaMateriale) {
		this.fkNaturaMateriale = fkNaturaMateriale;
	}
	public void setFkSpecie(Integer fkSpecie) {
		this.fkSpecie = fkSpecie;
	}
	public void setFkCategMateriale(Integer fkCategMateriale) {
		this.fkCategMateriale = fkCategMateriale;
	}
	public void setFkTipoMateriale(Integer fkTipoMateriale) {
		this.fkTipoMateriale = fkTipoMateriale;
	}
	public void setDestinazione(Integer destinazione) {
		this.destinazione = destinazione;
	}
	public void setFkPopolamento(Integer fkPopolamento) {
		this.fkPopolamento = fkPopolamento;
	}
	public void setMiscuglio(String miscuglio) {
		this.miscuglio = miscuglio;
	}
	public void setFkOrigine(Integer fkOrigine) {
		this.fkOrigine = fkOrigine;
	}
	public void setDescrizioneOrigine(String descrizioneOrigine) {
		this.descrizioneOrigine = descrizioneOrigine;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	public void setAltitudine(String altitudine) {
		this.altitudine = altitudine;
	}
	public void setAnnoMaturazioneSemi(Integer annoMaturazioneSemi) {
		this.annoMaturazioneSemi = annoMaturazioneSemi;
	}
	public void setQtaMaterialeBase(Double qtaMaterialeBase) {
		this.qtaMaterialeBase = qtaMaterialeBase;
	}
	public void setFkUdmMateriale(Integer fkUdmMateriale) {
		this.fkUdmMateriale = fkUdmMateriale;
	}
	public void setFlgPartitaPrecedente(String flgPartitaPrecedente) {
		this.flgPartitaPrecedente = flgPartitaPrecedente;
	}
	public void setnCertificatoPrecedente(String nCertificatoPrecedente) {
		this.nCertificatoPrecedente = nCertificatoPrecedente;
	}
	public void setQtaPartitaIniziale(Double qtaPartitaIniziale) {
		this.qtaPartitaIniziale = qtaPartitaIniziale;
	}
	public void setDurataAllevamento(String durataAllevamento) {
		this.durataAllevamento = durataAllevamento;
	}
	public void setFlgPropagazione(String flgPropagazione) {
		this.flgPropagazione = flgPropagazione;
	}
	public void setMetodoPropagazione(String metodoPropagazione) {
		this.metodoPropagazione = metodoPropagazione;
	}
	public void setnCicliPropagazione(String nCicliPropagazione) {
		this.nCicliPropagazione = nCicliPropagazione;
	}
	public void setDataCertificato(Date dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrigineNonAutoctoni() {
		return origineNonAutoctoni;
	}
	public void setOrigineNonAutoctoni(String origineNonAutoctoni) {
		this.origineNonAutoctoni = origineNonAutoctoni;
	}
	
	
}
