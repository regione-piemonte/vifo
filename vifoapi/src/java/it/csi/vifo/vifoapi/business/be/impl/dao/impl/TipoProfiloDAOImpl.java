/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.TipoProfiloDAO;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class TipoProfiloDAOImpl extends GenericDAO implements TipoProfiloDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public String getDescrizioneById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT descr_tipo_profilo FROM vivai_d_tipo_profilo cu WHERE cu.id_tipo_profilo = ?";
			return jdbcTemplate.queryForObject(sql, String.class, id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
