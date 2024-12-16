/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class CertificatoRiepilogo {
	
	private Integer idProvenienza;
	private Integer fkUdmMateriale;
	private String codiceCertificatoProvenienza;
	private String numeroCertificato;
	private String siglaProvincia;
	private String dataCertificato;
	private String nomeSpecie;
	private String descrCategMateriale;
	private String popolamentoSeme;
	
	public Integer getIdProvenienza() {
		return idProvenienza;
	}
	public String getCodiceCertificatoProvenienza() {
		return codiceCertificatoProvenienza;
	}
	public Integer getFkUdmMateriale() {
		return fkUdmMateriale;
	}
	public void setFkUdmMateriale(Integer fkUdmMateriale) {
		this.fkUdmMateriale = fkUdmMateriale;
	}
	public String getNumeroCertificato() {
		return numeroCertificato;
	}
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	public String getDataCertificato() {
		return dataCertificato;
	}
	public String getNomeSpecie() {
		return nomeSpecie;
	}
	public String getDescrCategMateriale() {
		return descrCategMateriale;
	}
	public String getPopolamentoSeme() {
		return popolamentoSeme;
	}
	public void setIdProvenienza(Integer idProvenienza) {
		this.idProvenienza = idProvenienza;
	}
	public void setCodiceCertificatoProvenienza(String codiceCertificatoProvenienza) {
		this.codiceCertificatoProvenienza = codiceCertificatoProvenienza;
	}
	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = numeroCertificato;
	}
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}
	public void setDataCertificato(String dataCertificato) {
		this.dataCertificato = dataCertificato;
	}
	public void setNomeSpecie(String nomeSpecie) {
		this.nomeSpecie = nomeSpecie;
	}
	public void setDescrCategMateriale(String descrCategMateriale) {
		this.descrCategMateriale = descrCategMateriale;
	}
	public void setPopolamentoSeme(String popolamentoSeme) {
		this.popolamentoSeme = popolamentoSeme;
	}
	
	

}
