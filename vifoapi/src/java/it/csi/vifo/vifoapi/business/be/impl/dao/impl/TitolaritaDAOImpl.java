/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.TitolaritaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.VoceDizionarioMapper;
import it.csi.vifo.vifoapi.dto.VoceDizionario;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class TitolaritaDAOImpl extends GenericDAO implements TitolaritaDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public List<VoceDizionario> getListTitolarita() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_titolarita as id, descr_titolarita as descrizione "
					+ "FROM vivai_d_titolarita where flg_attivo = 1 order by mtd_ordinamento ";
			return jdbcTemplate.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public VoceDizionario getTitolaritaById(Integer idTitolarita) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_titolarita as id, descr_titolarita as descrizione "
					+ "FROM vivai_d_titolarita where id_titolarita = ? order by mtd_ordinamento ";
			return jdbcTemplate.queryForObject(sql, new VoceDizionarioMapper(), idTitolarita);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
