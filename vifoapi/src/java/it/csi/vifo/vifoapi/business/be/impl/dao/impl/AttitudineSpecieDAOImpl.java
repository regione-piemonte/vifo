/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.AttitudineSpecieDAO;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class AttitudineSpecieDAOImpl extends GenericDAO implements AttitudineSpecieDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int deleteByIdSpecie(Integer idSpecie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_r_att_specie where id_specie = ?";
			return jdbcTemplate.update(sql,idSpecie);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void insertList(Integer idSpecie, List<Integer> idsAttidudine, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_r_att_specie ");  
			sql.append("(id_attitudine, id_specie, data_inserimento, fk_config_utente) "); 
			sql.append("VALUES(?, ?, ?, ?)");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Object[] values = new Object[] {0,idSpecie, now, idConfUtente};
			
			for(int id: idsAttidudine) {
				values[0] = id;
				jdbcTemplate.update(sql.toString(), values);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
