/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper.dto;

import java.io.Serializable;

import it.csi.vifo.vifobo.dto.ApiManagerDto;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;

public class InfoApiManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5728533725928368105L;
	
	private OauthHelper oauthHelper;
	private ApiManagerDto apiManagerDto;
	/**
	 * @return the oauthHelper
	 */
	public OauthHelper getOauthHelper() {
		return oauthHelper;
	}
	/**
	 * @param oauthHelper the oauthHelper to set
	 */
	public void setOauthHelper(OauthHelper oauthHelper) {
		this.oauthHelper = oauthHelper;
	}
	/**
	 * @return the apiManagerDto
	 */
	public ApiManagerDto getApiManagerDto() {
		return apiManagerDto;
	}
	/**
	 * @param apiManagerDto the apiManagerDto to set
	 */
	public void setApiManagerDto(ApiManagerDto apiManagerDto) {
		this.apiManagerDto = apiManagerDto;
	}
	
	
	
	
}
