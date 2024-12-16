/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import javax.servlet.http.HttpServletRequest;

import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.UserInfo;

public interface AdpforHomeService {
	
	ConfigUtente getConfigUtente(UserInfo user) throws RecordNotUniqueException;
	
	ConfigUtente getSessionConfigUtente(HttpServletRequest httpRequest) throws RecordNotUniqueException;
	
	TSoggetto getSessionUser(UserInfo user) throws RecordNotUniqueException;

}
