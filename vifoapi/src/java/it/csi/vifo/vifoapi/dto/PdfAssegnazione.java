/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.List;

public class PdfAssegnazione {
	private String tipoDocumento;
	private String direzione;
	private String settore;
	private String indirizzoSettore;
	private String capSettore;
	private String comuneSettore;
	private String telefonoSettore;
	private String dirigenteSettore;
	private String numeroProtocollo;
	private String dataProtocollo;
	private String numeroAssegnazione;
	private String dataAssegnazione;
	private String dataScadenzaRitiro;
	private String operatoreAssegnazione;
	private String scopoAssegnazioneGratuita;	
	private String totaleImporti;
	private String totalFattura;
	private String scontoApplicato;
	private String totaleSconto;
	private String totaleImponibile;
	private String aliquotaIva;
	private String totaleIva;
	private String noteVivaista;
	
	private List<PdfBene> listBeni;	
	
	private String beneficiario;
	private String indirizzoBeneficiario;
	private String capBeneficiario;
	private String comuneBeneficiario;
	private String telefonoBeneficiario;
	private String mailBeneficiario;
	private String pecBeneficiario;
	
	private String vivaio;
	private String indirizzoVivaio;
	private String capVivaio;
	private String comuneVivaio;
	private String siglaProvVivaio;
	private String telefonoVivaio;
	private String mailVivaio;
	private String pecVivaio;
	private String orariAperturaVivaio;
	
	private List<PdfTesto> listInformazioni;
	private String condizioniVenditaTitolo;
	private List<PdfTesto> listCondizioniVenditaParagrafi;
	
	private Boolean isBozza;
	
	public Boolean getIsBozza() {
		return isBozza;
	}
	public void setIsBozza(Boolean isBozza) {
		this.isBozza = isBozza;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDirezione() {
		return direzione;
	}
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public String getIndirizzoSettore() {
		return indirizzoSettore;
	}
	public void setIndirizzoSettore(String indirizzoSettore) {
		this.indirizzoSettore = indirizzoSettore;
	}
	public String getCapSettore() {
		return capSettore;
	}
	public void setCapSettore(String capSettore) {
		this.capSettore = capSettore;
	}
	public String getComuneSettore() {
		return comuneSettore;
	}
	public void setComuneSettore(String comuneSettore) {
		this.comuneSettore = comuneSettore;
	}
	public String getTelefonoSettore() {
		return telefonoSettore;
	}
	public void setTelefonoSettore(String telefonoSettore) {
		this.telefonoSettore = telefonoSettore;
	}
	public String getDirigenteSettore() {
		return dirigenteSettore;
	}
	public void setDirigenteSettore(String dirigenteSettore) {
		this.dirigenteSettore = dirigenteSettore;
	}
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	public String getDataProtocollo() {
		return dataProtocollo;
	}
	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public String getNumeroAssegnazione() {
		return numeroAssegnazione;
	}
	public void setNumeroAssegnazione(String numeroAssegnazione) {
		this.numeroAssegnazione = numeroAssegnazione;
	}
	public String getDataAssegnazione() {
		return dataAssegnazione;
	}
	public void setDataAssegnazione(String dataAssegnazione) {
		this.dataAssegnazione = dataAssegnazione;
	}	
	public String getDataScadenzaRitiro() {
		return dataScadenzaRitiro;
	}
	public void setDataScadenzaRitiro(String dataScadenzaRitiro) {
		this.dataScadenzaRitiro = dataScadenzaRitiro;
	}
	public String getOperatoreAssegnazione() {
		return operatoreAssegnazione;
	}
	public void setOperatoreAssegnazione(String operatoreAssegnazione) {
		this.operatoreAssegnazione = operatoreAssegnazione;
	}
	public String getScopoAssegnazioneGratuita() {
		return scopoAssegnazioneGratuita;
	}
	public void setScopoAssegnazioneGratuita(String scopoAssegnazioneGratuita) {
		this.scopoAssegnazioneGratuita = scopoAssegnazioneGratuita;
	}
	public String getTotaleImporti() {
		return totaleImporti;
	}
	public void setTotaleImporti(String totaleImporti) {
		this.totaleImporti = totaleImporti;
	}
	public String getTotalFattura() {
		return totalFattura;
	}
	public void setTotalFattura(String totalFattura) {
		this.totalFattura = totalFattura;
	}
	public String getScontoApplicato() {
		return scontoApplicato;
	}
	public void setScontoApplicato(String scontoApplicato) {
		this.scontoApplicato = scontoApplicato;
	}
	public String getTotaleSconto() {
		return totaleSconto;
	}
	public void setTotaleSconto(String totaleSconto) {
		this.totaleSconto = totaleSconto;
	}
	public String getTotaleImponibile() {
		return totaleImponibile;
	}
	public void setTotaleImponibile(String totaleImponibile) {
		this.totaleImponibile = totaleImponibile;
	}
	public String getAliquotaIva() {
		return aliquotaIva;
	}
	public void setAliquotaIva(String aliquotaIva) {
		this.aliquotaIva = aliquotaIva;
	}
	public String getTotaleIva() {
		return totaleIva;
	}
	public void setTotaleIva(String totaleIva) {
		this.totaleIva = totaleIva;
	}
	public String getNoteVivaista() {
		return noteVivaista;
	}
	public void setNoteVivaista(String noteVivaista) {
		this.noteVivaista = noteVivaista;
	}
	public List<PdfBene> getListBeni() {
		return listBeni;
	}
	public void setListBeni(List<PdfBene> listBeni) {
		this.listBeni = listBeni;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getIndirizzoBeneficiario() {
		return indirizzoBeneficiario;
	}
	public void setIndirizzoBeneficiario(String indirizzoBeneficiario) {
		this.indirizzoBeneficiario = indirizzoBeneficiario;
	}
	public String getCapBeneficiario() {
		return capBeneficiario;
	}
	public void setCapBeneficiario(String capBeneficiario) {
		this.capBeneficiario = capBeneficiario;
	}
	public String getComuneBeneficiario() {
		return comuneBeneficiario;
	}
	public void setComuneBeneficiario(String comuneBeneficiario) {
		this.comuneBeneficiario = comuneBeneficiario;
	}
	public String getTelefonoBeneficiario() {
		return telefonoBeneficiario;
	}
	public void setTelefonoBeneficiario(String telefonoBeneficiario) {
		this.telefonoBeneficiario = telefonoBeneficiario;
	}
	public String getMailBeneficiario() {
		return mailBeneficiario;
	}
	public void setMailBeneficiario(String mailBeneficiario) {
		this.mailBeneficiario = mailBeneficiario;
	}
	public String getPecBeneficiario() {
		return pecBeneficiario;
	}
	public void setPecBeneficiario(String pecBeneficiario) {
		this.pecBeneficiario = pecBeneficiario;
	}
	public String getVivaio() {
		return vivaio;
	}
	public void setVivaio(String vivaio) {
		this.vivaio = vivaio;
	}
	public String getIndirizzoVivaio() {
		return indirizzoVivaio;
	}
	public void setIndirizzoVivaio(String indirizzoVivaio) {
		this.indirizzoVivaio = indirizzoVivaio;
	}
	public String getCapVivaio() {
		return capVivaio;
	}
	public void setCapVivaio(String capVivaio) {
		this.capVivaio = capVivaio;
	}
	public String getComuneVivaio() {
		return comuneVivaio;
	}
	public void setComuneVivaio(String comuneVivaio) {
		this.comuneVivaio = comuneVivaio;
	}
	public String getSiglaProvVivaio() {
		return siglaProvVivaio;
	}
	public void setSiglaProvVivaio(String siglaProvVivaio) {
		this.siglaProvVivaio = siglaProvVivaio;
	}
	public String getTelefonoVivaio() {
		return telefonoVivaio;
	}
	public void setTelefonoVivaio(String telefonoVivaio) {
		this.telefonoVivaio = telefonoVivaio;
	}
	public String getMailVivaio() {
		return mailVivaio;
	}
	public void setMailVivaio(String mailVivaio) {
		this.mailVivaio = mailVivaio;
	}
	public String getPecVivaio() {
		return pecVivaio;
	}
	public void setPecVivaio(String pecVivaio) {
		this.pecVivaio = pecVivaio;
	}
	public String getOrariAperturaVivaio() {
		return orariAperturaVivaio;
	}
	public void setOrariAperturaVivaio(String orariAperturaVivaio) {
		this.orariAperturaVivaio = orariAperturaVivaio;
	}
	public List<PdfTesto> getListInformazioni() {
		return listInformazioni;
	}
	public void setListInformazioni(List<PdfTesto> listInformazioni) {
		this.listInformazioni = listInformazioni;
	}
	public String getCondizioniVenditaTitolo() {
		return condizioniVenditaTitolo;
	}
	public void setCondizioniVenditaTitolo(String condizioniVenditaTitolo) {
		this.condizioniVenditaTitolo = condizioniVenditaTitolo;
	}
	public List<PdfTesto> getListCondizioniVenditaParagrafi() {
		return listCondizioniVenditaParagrafi;
	}
	public void setListCondizioniVenditaParagrafi(List<PdfTesto> listCondizioniVenditaParagrafi) {
		this.listCondizioniVenditaParagrafi = listCondizioniVenditaParagrafi;
	}
	
}
