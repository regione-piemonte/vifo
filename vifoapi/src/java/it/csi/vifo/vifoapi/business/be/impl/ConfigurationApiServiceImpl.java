/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.ConfigurationApi;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifoapi.util.TipoParametroApplEnum;


@Component
public class ConfigurationApiServiceImpl extends BaseApiServiceImpl implements ConfigurationApi {

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	private ConfigurationDAO configurationDAO;

	@Override
	public Response getConfigurationByName(String name, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        TipoParametroApplEnum tipoParametro = TipoParametroApplEnum.valueOf(name);
	        Map<String,String> mapConf = new HashMap<String,String>();
			mapConf.put("value",configurationDAO.getConfigurationByTipoParametro(tipoParametro));
			return Response.ok(mapConf).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getConfigurationPagamenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(configurationDAO.getConfigPagamentiFe()).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
