/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifobo.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.TSoggettoMapper;
import it.csi.vifo.vifobo.dto.TSoggetto;
import it.csi.vifo.vifobo.util.GenericDAO;


@Component
public class SoggettoDAOImpl extends GenericDAO implements SoggettoDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	private final TSoggettoMapper soggettoMapper = new TSoggettoMapper();

	@Override
	public TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_soggetto s where s.id_soggetto=?";
			List<TSoggetto> soggettos = jdbcTemplate.query(sql, soggettoMapper, idSoggetto);
			if (soggettos.isEmpty()) {
				throw new RecordNotFoundException();
			} else {
				return soggettos.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
