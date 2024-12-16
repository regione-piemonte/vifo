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

import it.csi.vifo.vifoapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DestinazioneMapper;
import it.csi.vifo.vifoapi.dto.Destinazione;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class DestinazioneDAOImpl extends GenericDAO implements DestinazioneDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int insert(Destinazione item) {	
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_catasto");
			sql.append("(fk_domanda, istat_comune, sezione, foglio, particella, metri_lineari, metri_quadri, ");
			sql.append("data_inserimento, fk_config_utente)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?)");
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"item.getSezione(): " +  item.getSezione()));
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(item.getFkDomanda());
			parameters.add(item.getIstatComune());
			parameters.add(item.getSezione());
			parameters.add(item.getFoglio());
			parameters.add(item.getParticella());
			parameters.add(item.getMetriLineari());
			parameters.add(item.getMetriQuadri());
			parameters.add(now);
			parameters.add(item.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_catasto", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<Destinazione> getDestinazioniByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select * from vivai_t_catasto where fk_domanda = ? ";
			return jdbcTemplate.query(sql, new DestinazioneMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public int deleteByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_catasto where fk_domanda = ?";
			return jdbcTemplate.update(sql,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
