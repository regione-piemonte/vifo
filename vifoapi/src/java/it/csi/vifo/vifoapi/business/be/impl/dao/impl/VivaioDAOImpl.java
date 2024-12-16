/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.VivaioDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.VivaioMapper;
import it.csi.vifo.vifoapi.dto.Vivaio;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class VivaioDAOImpl extends GenericDAO implements VivaioDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public List<Vivaio> getListVivaio() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_vivaio where id_vivaio > 0 order by nome ";
			return jdbcTemplate.query(sql, new VivaioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Vivaio getVivaioById(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_vivaio where id_vivaio = ? ";
			return jdbcTemplate.queryForObject(sql, new VivaioMapper(), idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
