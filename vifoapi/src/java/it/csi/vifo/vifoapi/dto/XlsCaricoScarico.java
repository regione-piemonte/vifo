/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class XlsCaricoScarico {
/*
 * vip.id_inventario_piante, vip.data_carico as data_movimento,vip.nome_comune as specie, vip.provenienza, vip.certificato_provenienza, 
 vip.qta_carico, null as qta_ritirata, null as id_domanda, 
	null as numero_ddt, null as tipo_domanda, null as richiedente
 */
	private int idInventario;
	private String dataMovimento;
	private String specie;
	private String provenienza;
	private String certificatoProvenienza;
	private Integer qtaCarico;
	private Integer qtaScarico;
	private Integer idDomanda;
	private String numeroDdt;
	private String tipoDomanda;
	private String richiedente;
	
	public int getIdInventario() {
		return idInventario;
	}
	public String getDataMovimento() {
		return dataMovimento;
	}
	public String getSpecie() {
		return specie;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public String getCertificatoProvenienza() {
		return certificatoProvenienza;
	}
	public Integer getQtaCarico() {
		return qtaCarico;
	}
	public Integer getQtaScarico() {
		return qtaScarico;
	}
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public String getNumeroDdt() {
		return numeroDdt;
	}
	public String getTipoDomanda() {
		return tipoDomanda;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setCertificatoProvenienza(String certificatoProvenienza) {
		this.certificatoProvenienza = certificatoProvenienza;
	}
	public void setQtaCarico(Integer qtaCarico) {
		this.qtaCarico = qtaCarico;
	}
	public void setQtaScarico(Integer qtaScarico) {
		this.qtaScarico = qtaScarico;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setNumeroDdt(String numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
	public void setTipoDomanda(String tipoDomanda) {
		this.tipoDomanda = tipoDomanda;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	
}
