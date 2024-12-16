/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.List;

public class PdfDomanda {
	private String direzione;
	private String settore;
	private String indirizzoSettore;
	private String capSettore;
	private String comuneSettore;
	private String telefonoSettore;
	private String dirigenteSettore;
	
	private String numDomanda;
	private String dataDomanda;
	private String operatore;
	private String numMarcaBollo;
	
	private String beneficiario;
	private String cfBeneficiario;
	private String pivaBeneficiario;
	private String pecBeneficiario;
	private String indirizzoBeneficiario;
	private String capBeneficiario;
	private String comuneBeneficiario;
	private String telefonoBeneficiario;
	private String mailBeneficiario;
	private String isEntePubblico;
	
	private String titolarita;
	private String note;
	private String richiestoBollo;
	private String scopoAssegnazione;
	
	private String nomeVivaio;
	private String indirizzoVivaio;
	private String capVivaio;
	private String comuneVivaio;
	private String siglaProvVivaio;
	private String telefonoVivaio;
	private String tipoDocumento;
	private String tipoDomanda;
	
	private String titlePrivacy;
	private List<String> listParagrafiPrivacy;
	
	private List<String> listNoteDinamiche;	
	private List<Destinazione> listDestinazioni;
	private List<SpecieRichiesta> listSpecieRichieste;
	private List<PdfAttitudine> listAttitudineSpecieRichieste;
	
	public String getDirezione() {
		return direzione;
	}
	public String getSettore() {
		return settore;
	}
	public String getIndirizzoSettore() {
		return indirizzoSettore;
	}
	public String getCapSettore() {
		return capSettore;
	}
	public String getComuneSettore() {
		return comuneSettore;
	}
	public String getTelefonoSettore() {
		return telefonoSettore;
	}
	public String getNumDomanda() {
		return numDomanda;
	}
	public String getDataDomanda() {
		return dataDomanda;
	}
	public String getOperatore() {
		return operatore;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public String getCfBeneficiario() {
		return cfBeneficiario;
	}
	public String getPivaBeneficiario() {
		return pivaBeneficiario;
	}
	public String getPecBeneficiario() {
		return pecBeneficiario;
	}
	public String getIndirizzoBeneficiario() {
		return indirizzoBeneficiario;
	}
	public String getCapBeneficiario() {
		return capBeneficiario;
	}
	public String getComuneBeneficiario() {
		return comuneBeneficiario;
	}
	public String getTelefonoBeneficiario() {
		return telefonoBeneficiario;
	}
	public String getMailBeneficiario() {
		return mailBeneficiario;
	}
	public String getIsEntePubblico() {
		return isEntePubblico;
	}
	public String getTitolarita() {
		return titolarita;
	}
	public String getNote() {
		return note;
	}
	public String getScopoAssegnazione() {
		return scopoAssegnazione;
	}
	public String getNomeVivaio() {
		return nomeVivaio;
	}
	public String getIndirizzoVivaio() {
		return indirizzoVivaio;
	}
	public String getCapVivaio() {
		return capVivaio;
	}
	public String getComuneVivaio() {
		return comuneVivaio;
	}
	public String getSiglaProvVivaio() {
		return siglaProvVivaio;
	}
	public String getTelefonoVivaio() {
		return telefonoVivaio;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public String getTipoDomanda() {
		return tipoDomanda;
	}
	public String getTitlePrivacy() {
		return titlePrivacy;
	}
	public List<String> getListParagrafiPrivacy() {
		return listParagrafiPrivacy;
	}
	public List<String> getListNoteDinamiche() {
		return listNoteDinamiche;
	}
	public List<Destinazione> getListDestinazioni() {
		return listDestinazioni;
	}
	public List<SpecieRichiesta> getListSpecieRichieste() {
		return listSpecieRichieste;
	}
	public List<PdfAttitudine> getListAttitudineSpecieRichieste() {
		return listAttitudineSpecieRichieste;
	}
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public void setIndirizzoSettore(String indirizzoSettore) {
		this.indirizzoSettore = indirizzoSettore;
	}
	public void setCapSettore(String capSettore) {
		this.capSettore = capSettore;
	}
	public void setComuneSettore(String comuneSettore) {
		this.comuneSettore = comuneSettore;
	}
	public void setTelefonoSettore(String telefonoSettore) {
		this.telefonoSettore = telefonoSettore;
	}
	public void setNumDomanda(String numDomanda) {
		this.numDomanda = numDomanda;
	}
	public void setDataDomanda(String dataDomanda) {
		this.dataDomanda = dataDomanda;
	}
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public void setCfBeneficiario(String cfBeneficiario) {
		this.cfBeneficiario = cfBeneficiario;
	}
	public void setPivaBeneficiario(String pivaBeneficiario) {
		this.pivaBeneficiario = pivaBeneficiario;
	}
	public void setPecBeneficiario(String pecBeneficiario) {
		this.pecBeneficiario = pecBeneficiario;
	}
	public void setIndirizzoBeneficiario(String indirizzoBeneficiario) {
		this.indirizzoBeneficiario = indirizzoBeneficiario;
	}
	public void setCapBeneficiario(String capBeneficiario) {
		this.capBeneficiario = capBeneficiario;
	}
	public void setComuneBeneficiario(String comuneBeneficiario) {
		this.comuneBeneficiario = comuneBeneficiario;
	}
	public void setTelefonoBeneficiario(String telefonoBeneficiario) {
		this.telefonoBeneficiario = telefonoBeneficiario;
	}
	public void setMailBeneficiario(String mailBeneficiario) {
		this.mailBeneficiario = mailBeneficiario;
	}
	public void setIsEntePubblico(String isEntePubblico) {
		this.isEntePubblico = isEntePubblico;
	}
	public void setTitolarita(String titolarita) {
		this.titolarita = titolarita;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setScopoAssegnazione(String scopoAssegnazione) {
		this.scopoAssegnazione = scopoAssegnazione;
	}
	public void setNomeVivaio(String nomeVivaio) {
		this.nomeVivaio = nomeVivaio;
	}
	public void setIndirizzoVivaio(String indirizzoVivaio) {
		this.indirizzoVivaio = indirizzoVivaio;
	}
	public void setCapVivaio(String capVivaio) {
		this.capVivaio = capVivaio;
	}
	public void setComuneVivaio(String comuneVivaio) {
		this.comuneVivaio = comuneVivaio;
	}
	public void setSiglaProvVivaio(String siglaProvVivaio) {
		this.siglaProvVivaio = siglaProvVivaio;
	}
	public void setTelefonoVivaio(String telefonoVivaio) {
		this.telefonoVivaio = telefonoVivaio;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setTipoDomanda(String tipoDomanda) {
		this.tipoDomanda = tipoDomanda;
	}
	public void setTitlePrivacy(String titlePrivacy) {
		this.titlePrivacy = titlePrivacy;
	}
	public void setListParagrafiPrivacy(List<String> listParagrafiPrivacy) {
		this.listParagrafiPrivacy = listParagrafiPrivacy;
	}
	public void setListNoteDinamiche(List<String> listNoteDinamiche) {
		this.listNoteDinamiche = listNoteDinamiche;
	}
	public void setListDestinazioni(List<Destinazione> listDestinazioni) {
		this.listDestinazioni = listDestinazioni;
	}
	public void setListSpecieRichieste(List<SpecieRichiesta> listSpecieRichieste) {
		this.listSpecieRichieste = listSpecieRichieste;
	}
	public void setListAttitudineSpecieRichieste(List<PdfAttitudine> listAttitudineSpecieRichieste) {
		this.listAttitudineSpecieRichieste = listAttitudineSpecieRichieste;
	}
	public String getDirigenteSettore() {
		return dirigenteSettore;
	}
	public void setDirigenteSettore(String dirigenteSettore) {
		this.dirigenteSettore = dirigenteSettore;
	}
	public String getNumMarcaBollo() {
		return numMarcaBollo;
	}
	public void setNumMarcaBollo(String numMarcaBollo) {
		this.numMarcaBollo = numMarcaBollo;
	}
	public String getRichiestoBollo() {
		return richiestoBollo;
	}
	public void setRichiestoBollo(String richiestoBollo) {
		this.richiestoBollo = richiestoBollo;
	}
	
}
