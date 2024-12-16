/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

public class ConfigPagamenti {
	private Long idProcedimento;
	private String codicePagamentoCorrispettivo;
	private String codicePagamentoFattura;
	private String ruoloIride;
	private Integer livelloIride;
	private String webAppUrl;
	private String webServiceUrl;
	private String webServiceUser;
	private String webServicePwd;
	private String ipsEnabledForCallbackRiceviRt;
	
	public Long getIdProcedimento() {
		return idProcedimento;
	}
	public String getCodicePagamentoCorrispettivo() {
		return codicePagamentoCorrispettivo;
	}
	public String getCodicePagamentoFattura() {
		return codicePagamentoFattura;
	}
	public String getRuoloIride() {
		return ruoloIride;
	}
	public Integer getLivelloIride() {
		return livelloIride;
	}
	public String getWebAppUrl() {
		return webAppUrl;
	}
	public String getWebServiceUrl() {
		return webServiceUrl;
	}
	public String getWebServiceUser() {
		return webServiceUser;
	}
	public String getWebServicePwd() {
		return webServicePwd;
	}
	public void setIdProcedimento(Long idProcedimento) {
		this.idProcedimento = idProcedimento;
	}
	public void setCodicePagamentoCorrispettivo(String codicePagamentoCorrispettivo) {
		this.codicePagamentoCorrispettivo = codicePagamentoCorrispettivo;
	}
	public void setCodicePagamentoFattura(String codicePagamentoFattura) {
		this.codicePagamentoFattura = codicePagamentoFattura;
	}
	public void setRuoloIride(String ruoloIride) {
		this.ruoloIride = ruoloIride;
	}
	public void setLivelloIride(Integer livelloIride) {
		this.livelloIride = livelloIride;
	}
	public void setWebAppUrl(String webAppUrl) {
		this.webAppUrl = webAppUrl;
	}
	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}
	public void setWebServiceUser(String webServiceUser) {
		this.webServiceUser = webServiceUser;
	}
	public void setWebServicePwd(String webServicePwd) {
		this.webServicePwd = webServicePwd;
	}
	public String getIpsEnabledForCallbackRiceviRt() {
		return ipsEnabledForCallbackRiceviRt;
	}
	public void setIpsEnabledForCallbackRiceviRt(String ipsEnabledForCallbackRiceviRt) {
		this.ipsEnabledForCallbackRiceviRt = ipsEnabledForCallbackRiceviRt;
	}
	
	
}
