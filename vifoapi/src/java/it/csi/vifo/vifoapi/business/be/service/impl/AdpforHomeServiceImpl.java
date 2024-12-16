/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.UserInfo;
import it.csi.vifo.vifoapi.util.Constants;

@Component
public class AdpforHomeServiceImpl extends BaseApiServiceImpl implements AdpforHomeService {
	
	public static final String LOGGED_CONFIG = "loggedConfig";
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired
	ConfigUtenteDAO configUtenteDAO;
	
	@Override
	public ConfigUtente getConfigUtente(UserInfo user) throws RecordNotUniqueException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
			return configUtenteDAO.getConfigUtenteBySoggettoId(soggetto.getIdSoggetto());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public TSoggetto getSessionUser(UserInfo user) throws RecordNotUniqueException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public ConfigUtente getSessionConfigUtente(HttpServletRequest httpRequest) throws RecordNotUniqueException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = (ConfigUtente) httpRequest.getSession().getAttribute(LOGGED_CONFIG);
			if(confUtente == null) {
				UserInfo userInfo = (UserInfo)httpRequest.getSession().getAttribute(Constants.USERINFO_SESSIONATTR);
				TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
				if(soggetto != null) {					
					confUtente = configUtenteDAO.getConfigUtenteBySoggettoId(soggetto.getIdSoggetto());					
				}
			}
			return confUtente;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	

}
