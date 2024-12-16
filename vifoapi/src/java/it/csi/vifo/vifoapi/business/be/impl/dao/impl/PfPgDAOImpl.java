/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.PfPgDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PfPgMapper;
import it.csi.vifo.vifoapi.dto.PfPg;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class PfPgDAOImpl extends GenericDAO implements PfPgDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int createPgPg(PfPg pfPg) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "INSERT INTO vivai_r_pf_pg (id_soggetto_pf, id_soggetto_pg, data_inserimento,fk_config_utente) "
				+ "VALUES (?,?,?,?)";
		
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(pfPg.getIdSoggettoPf());
			parameters.add(pfPg.getIdSoggettoPg());
			parameters.add(now);
			parameters.add(pfPg.getFkConfigUtente());
			return jdbcTemplate.update(sql, parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PfPg getByPfAndPg(Integer idPf, Integer idPg) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_r_pf_pg WHERE id_soggetto_pf = ?  AND id_soggetto_pg = ?";
			List<PfPg> list = jdbcTemplate.query(sql, new Object[] {idPf,idPg}, new PfPgMapper());
			return list == null || list.size()== 0? null: list.get(0);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
