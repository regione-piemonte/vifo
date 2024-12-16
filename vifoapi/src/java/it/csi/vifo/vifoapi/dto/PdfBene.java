/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class PdfBene {
	private String nomeSpecie;
	private Integer quantita;
	private Integer idPartita;
	private String codiceTracciabilita;
	private String certificatoProv;
	private String provenienza;
	private String eta;
	private String allevamento;
	private String prezzoUnitario;
	private String prezzoTotale;
	
	
	public String getNomeSpecie() {
		return nomeSpecie;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public Integer getIdPartita() {
		return idPartita;
	}
	public String getCodiceTracciabilita() {
		return codiceTracciabilita;
	}
	public void setCodiceTracciabilita(String codiceTracciabilita) {
		this.codiceTracciabilita = codiceTracciabilita;
	}
	public String getCertificatoProv() {
		return certificatoProv;
	}
	public void setNomeSpecie(String nomeSpecie) {
		this.nomeSpecie = nomeSpecie;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}
	public void setCertificatoProv(String certificatoProv) {
		this.certificatoProv = certificatoProv;
	}
	public String getAllevamento() {
		return allevamento;
	}
	public String getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public String getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setAllevamento(String allevamento) {
		this.allevamento = allevamento;
	}
	public void setPrezzoUnitario(String prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public void setPrezzoTotale(String prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	
	@Override
	public String toString() {
		return "PdfBene [nomeSpecie=" + nomeSpecie + ", quantita=" + quantita + ", idPartita=" + idPartita
				+ ", codiceTracciabilita=" + codiceTracciabilita + ", certificatoProv=" + certificatoProv
				+ ", provenienza=" + provenienza + ", eta=" + eta + ", allevamento=" + allevamento + ", prezzoUnitario="
				+ prezzoUnitario + ", prezzoTotale=" + prezzoTotale + "]";
	}
	
}
