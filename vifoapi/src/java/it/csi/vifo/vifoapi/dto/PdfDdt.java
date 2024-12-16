/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;
import java.util.List;

public class PdfDdt {
	private String direzione;
	private String settore;
	private String beneficiario;
	private String indirizzoBeneficiario;
	private String capBeneficiario;
	private String comuneBeneficiario;
	private String cfBeneficiario;
	private String pivaBeneficiario;
	private String numeroDdt;
	private String dataDdt;
	private Integer idDomanda;
	private String numeroFatturaAcconto;
	private String dataFatturaAcconto;
	
	public String getNumeroFatturaAcconto() {
		return numeroFatturaAcconto;
	}
	public void setNumeroFatturaAcconto(String numeroFatturaAcconto) {
		this.numeroFatturaAcconto = numeroFatturaAcconto;
	}
	public String getDataFatturaAcconto() {
		return dataFatturaAcconto;
	}
	public void setDataFatturaAcconto(String dataFatturaAcconto) {
		this.dataFatturaAcconto = dataFatturaAcconto;
	}
	private List<PdfBene> listBeni;
	
	public String getDirezione() {
		return direzione;
	}
	public String getSettore() {
		return settore;
	}
	public String getBeneficiario() {
		return beneficiario;
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
	public String getNumeroDdt() {
		return numeroDdt;
	}
	public String getDataDdt() {
		return dataDdt;
	}
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public List<PdfBene> getListBeni() {
		return listBeni;
	}
	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
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
	public void setNumeroDdt(String numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
	public void setDataDdt(String dataDdt) {
		this.dataDdt = dataDdt;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setListBeni(List<PdfBene> listBeni) {
		this.listBeni = listBeni;
	}
	public String getCfBeneficiario() {
		return cfBeneficiario;
	}
	public String getPivaBeneficiario() {
		return pivaBeneficiario;
	}
	public void setCfBeneficiario(String cfBeneficiario) {
		this.cfBeneficiario = cfBeneficiario;
	}
	public void setPivaBeneficiario(String pivaBeneficiario) {
		this.pivaBeneficiario = pivaBeneficiario;
	}
	
}
