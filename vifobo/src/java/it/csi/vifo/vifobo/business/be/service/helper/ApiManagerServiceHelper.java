/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper;

import java.io.IOException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.vifo.vifobo.business.be.exception.DAOException;
import it.csi.vifo.vifobo.business.be.impl.dao.ApiManagerDao;
import it.csi.vifo.vifobo.business.be.service.helper.dto.InfoApiManager;
import it.csi.vifo.vifobo.dto.ApiManagerDto;
import it.csi.vifo.vifobo.util.ApiInternetEnum;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.WsProvider;
import it.csi.wso2.apiman.oauth2.helper.extra.axis1.Axis1Impl;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;


public class ApiManagerServiceHelper extends AbstractServiceHelper{

	
	private OauthHelper oauthHelper;
	private String idfTokenUrl;
	
	public static final String X_AUTH = "X-Authorization";
	
	public ApiManagerServiceHelper(String idfTokenUrl){
		this.idfTokenUrl = idfTokenUrl;
	}
	
	@Autowired
	private ApiManagerDao apiManagerDao;

	public final OauthHelper getOauthHelper(ApiManagerDto dto) throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] BEGIN");
	    	
		if (this.oauthHelper == null) {
			this.oauthHelper = new OauthHelper(
					this.idfTokenUrl,
					dto.getConsumerKey(),
					dto.getConsumerSecret(), 10000);
		}
		
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] END");
		return this.oauthHelper;
		

	}
	
	public TokenRetryManager getTokenRetryManagerCxf(String credenziali) throws IOException {
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerCxf] BEGIN");
		final TokenRetryManager trm = new TokenRetryManager();
		if(StringUtils.isNotEmpty(credenziali)) {
			trm.putHeader(X_AUTH, "Basic " + this.getJaasAutentication(credenziali));
		}
		trm.setOauthHelper(this.getApiManagerConfig().getOauthHelper());
		
		WsProvider wsp = new CxfImpl();
		System.out.println("WsProvider id " + wsp.getProviderId());
		
		trm.setWsProvider(wsp);
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerCxf] END");
		return trm;
	}
	
	public TokenRetryManager getTokenRetryManagerCxf() throws IOException {
		return this.getTokenRetryManagerCxf(null);
	}
	
	public TokenRetryManager getTokenRetryManagerAxis1()throws IOException{
		return this.getTokenRetryManagerAxis1(null);
	}
	
	public TokenRetryManager getTokenRetryManagerAxis1(String credenziali)throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerAxis1] BEGIN");
		final TokenRetryManager trm = new TokenRetryManager();
		trm.setOauthHelper(this.getApiManagerConfig().getOauthHelper());
		if(StringUtils.isNotEmpty(credenziali)) {
			trm.putHeader(X_AUTH, "Basic " + this.getJaasAutentication(credenziali));
		}
		WsProvider wsp = new Axis1Impl();
		
		trm.setWsProvider(wsp);
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerAxis1] END");
		return trm;
	}
	
	private String getJaasAutentication(String credenziali) {
		// Autenticazione con JAAS
		String encoded = Base64.getEncoder().encodeToString(credenziali.getBytes()); // new String(Base64.(credenziali.getBytes()));
		System.out.println("encodedBytes " + encoded);
		return encoded;
	}
	
	public final InfoApiManager getApiCosmoManagerConfig() throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] BEGIN");
		
		InfoApiManager result = new InfoApiManager();
	
		result.setApiManagerDto(this.getApiManagerInfo(ApiInternetEnum.API_COSMO));
		result.setOauthHelper(this.getOauthHelper(result.getApiManagerDto()));
		
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] END");
		return result;
	}
	
	public final InfoApiManager getApiManagerConfig() throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] BEGIN");
		
		InfoApiManager result = new InfoApiManager();
	
		result.setApiManagerDto(this.getApiManagerInfo(ApiInternetEnum.API_MANAGER));
		result.setOauthHelper(this.getOauthHelper(result.getApiManagerDto()));
		
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] END");
		return result;
	}

	private ApiManagerDto getApiManagerInfo(ApiInternetEnum apiInternet){
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerInfo] BEGIN");
		ApiManagerDto result = null;
		
		try {
			result = apiManagerDao.findParametriByApiInternet(apiInternet.getValue());
		} catch (DAOException e) {
			LOGGER.error("[ApiManagerServiceHelper::getApiManagerInfo]", e);
		}		
		
		result.setUrl(this.idfTokenUrl);
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerInfo] END");
		return result;
	}

}
