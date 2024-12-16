/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class PdfEtichetta {
	
	private String direzione;
	private String settore;
	private String nomeVivaio;
	private String indirizzoVivaio;
	private String comuneVivaio;
	private String capVivaio;
	private Integer idPartita;
	private String certificatoProvenienza;
	private String nomeSpecie;
	private String materialeBase;
	private String eta;
	private String altezza;
	private String allevamento;
	private String provenienza;
	private Integer quantita;

	private boolean flag386;
	private boolean flagZP;
	private String codiceAutorizzativo;
	private String codiceTracciabilita;
	private String siglaVivaio;

	public String getDirezione() {
		return direzione;
	}
	public String getSettore() {
		return settore;
	}
	public String getNomeVivaio() {
		return nomeVivaio;
	}
	public String getIndirizzoVivaio() {
		return indirizzoVivaio;
	}
	public String getComuneVivaio() {
		return comuneVivaio;
	}
	public String getCapVivaio() {
		return capVivaio;
	}
	public Integer getIdPartita() {
		return idPartita;
	}
	public String getCertificatoProvenienza() {
		return certificatoProvenienza;
	}
	public String getNomeSpecie() {
		return nomeSpecie;
	}
	public String getMaterialeBase() {
		return materialeBase;
	}
	public String getEta() {
		return eta;
	}
	public String getAltezza() {
		return altezza;
	}
	public String getAllevamento() {
		return allevamento;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public void setNomeVivaio(String nomeVivaio) {
		this.nomeVivaio = nomeVivaio;
	}
	public void setIndirizzoVivaio(String indirizzoVivaio) {
		this.indirizzoVivaio = indirizzoVivaio;
	}
	public void setComuneVivaio(String comuneVivaio) {
		this.comuneVivaio = comuneVivaio;
	}
	public void setCapVivaio(String capVivaio) {
		this.capVivaio = capVivaio;
	}
	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}
	public void setCertificatoProvenienza(String certificatoProvenienza) {
		this.certificatoProvenienza = certificatoProvenienza;
	}
	public void setNomeSpecie(String nomeSpecie) {
		this.nomeSpecie = nomeSpecie;
	}
	public void setMaterialeBase(String materialeBase) {
		this.materialeBase = materialeBase;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}
	public void setAllevamento(String allevamento) {
		this.allevamento = allevamento;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public boolean isFlag386() {
		return flag386;
	}

	public void setFlag386(boolean flag386) {
		this.flag386 = flag386;
	}

	public String getCodiceAutorizzativo() {
		return codiceAutorizzativo;
	}

	public void setCodiceAutorizzativo(String codiceAutorizzativo) {
		this.codiceAutorizzativo = codiceAutorizzativo;
	}

	public String getCodiceTracciabilita() {
		return codiceTracciabilita;
	}

	public void setCodiceTracciabilita(String codiceTracciabilita) {
		this.codiceTracciabilita = codiceTracciabilita;
	}

	public String getSiglaVivaio() {
		return siglaVivaio;
	}

	public void setSiglaVivaio(String siglaVivaio) {
		this.siglaVivaio = siglaVivaio;
	}

	public boolean isFlagZP() {
		return flagZP;
	}

	public void setFlagZP(boolean flagZP) {
		this.flagZP = flagZP;
	}
}
