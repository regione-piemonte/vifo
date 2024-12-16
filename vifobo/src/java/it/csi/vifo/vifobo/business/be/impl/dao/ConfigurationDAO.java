/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;

import it.csi.vifo.vifobo.dto.ConfigPagamenti;
import it.csi.vifo.vifobo.util.ParamsActaEnum;


public interface ConfigurationDAO {

	ConfigPagamenti getConfigPagamentiBe();
	String getParamActaByCode(ParamsActaEnum code);
	
}
