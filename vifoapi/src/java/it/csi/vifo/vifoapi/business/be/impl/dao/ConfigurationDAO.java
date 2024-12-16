/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.ConfigPagamenti;
import it.csi.vifo.vifoapi.dto.PdfTesto;
import it.csi.vifo.vifoapi.util.ParamsActaEnum;
import it.csi.vifo.vifoapi.util.TipoParametroApplEnum;

public interface ConfigurationDAO {

	String getConfigurationByTipoParametro(TipoParametroApplEnum tipoParametro);
	List<String> getListConfigurationsByTipoParametro(TipoParametroApplEnum tipoParametro);
	List<PdfTesto> getListTestiByTipoParametro(TipoParametroApplEnum tipoParametro);
	ConfigPagamenti getConfigPagamentiBe();
	ConfigPagamenti getConfigPagamentiFe();
	String getParamActaByCode(ParamsActaEnum code);
	
}
