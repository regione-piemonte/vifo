/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.util.performance.StopWatch;
import it.csi.vifo.vifobo.business.be.exception.DAOException;
import it.csi.vifo.vifobo.business.be.impl.dao.ApiManagerDao;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.ApiManagerDaoRowMapper;
import it.csi.vifo.vifobo.dto.ApiManagerDto;
import it.csi.vifo.vifobo.util.Constants;
import it.csi.vifo.vifobo.util.GenericDAO;

@Component
public class ApiManagerDaoImpl extends GenericDAO implements ApiManagerDao {
	private final String CLASSNAME = this.getClass().getSimpleName();
	

	public ApiManagerDto findParametriByApiInternet(int id) throws DAOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
					
        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));

		String sql = "SELECT * FROM vivai_cnf_param_apimgr WHERE flg_api_internet = ?";
			
		try {
			stopWatch.start();
			return jdbcTemplate.queryForObject(sql, new ApiManagerDaoRowMapper(), id);
		} catch (RuntimeException ex) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
			throw new DAOException("Configurazione non trovata");
		} finally {
			stopWatch.dumpElapsed(CLASSNAME, methodName, "esecuzione query", sql + " - flg_api_internet= " + id);
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	
	}

}
