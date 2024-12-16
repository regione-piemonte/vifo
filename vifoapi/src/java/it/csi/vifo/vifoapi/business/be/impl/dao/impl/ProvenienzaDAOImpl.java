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

import it.csi.vifo.vifoapi.business.be.impl.dao.ProvenienzaDAO;
import it.csi.vifo.vifoapi.dto.Provenienza;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class ProvenienzaDAOImpl extends GenericDAO implements ProvenienzaDAO{
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int insert(Provenienza provenienza) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_provenienza "); 
			sql.append("(fk_specie, fk_categ_materiale, provenienza, produttore, data_inserimento, ");
			sql.append("fk_config_utente, flg_no_fini_forestali) "); 
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
	
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(provenienza.getFkSpecie());		
			parameters.add(provenienza.getFkCategMateriale());
			parameters.add(provenienza.getProvenienza());
			parameters.add(provenienza.getProduttore());
			parameters.add(now);
			parameters.add(provenienza.getFkConfigUtente());
			parameters.add(provenienza.getFlgNoFiniForestali());
	
			return AIKeyHolderUtil.updateWithGenKey("id_provenienza", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(Provenienza provenienza) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_provenienza "); 
			sql.append("SET fk_specie= ?, fk_categ_materiale= ?, provenienza= ?, produttore= ?, ");
			sql.append("data_aggiornamento= ?, fk_config_utente= ?, flg_no_fini_forestali= ? ");
			sql.append("WHERE id_provenienza= ?");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(provenienza.getFkSpecie());
			parameters.add(provenienza.getFkCategMateriale());
			parameters.add(provenienza.getProvenienza());
			parameters.add(provenienza.getProduttore());
			parameters.add(now);
			parameters.add(provenienza.getFkConfigUtente());
			parameters.add(provenienza.getFlgNoFiniForestali());
			parameters.add(provenienza.getIdProvenienza());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
