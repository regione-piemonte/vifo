/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.ScopoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.ScopoMapper;
import it.csi.vifo.vifoapi.dto.Scopo;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class ScopoDAOImpl extends GenericDAO implements ScopoDAO {

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public List<Scopo> getListScopo() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_d_scopo where flg_attivo = 1 order by mtd_ordinamento ";
			return jdbcTemplate.query(sql, new ScopoMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Scopo getScopoById(Integer idScopo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_d_scopo where id_scopo = ? ";
			return jdbcTemplate.queryForObject(sql, new ScopoMapper(),idScopo);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
