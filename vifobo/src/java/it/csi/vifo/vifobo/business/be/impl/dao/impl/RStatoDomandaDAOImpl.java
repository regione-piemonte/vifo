/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.impl.dao.RStatoDomandaDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.RStatoDomandaMapper;
import it.csi.vifo.vifobo.dto.RStatoDomanda;
import it.csi.vifo.vifobo.util.GenericDAO;
import it.csi.vifo.vifobo.util.StatoDomandaEnum;

@Component
public class RStatoDomandaDAOImpl extends GenericDAO implements RStatoDomandaDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int insert(RStatoDomanda statoDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sqlUpd = "update vivai_r_stato_domanda set data_fine_stato = ? "
				+ "where id_domanda = ? and data_fine_stato is null ";
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(now);
			parameters.add(statoDomanda.getIdDomanda());
			jdbcTemplate.update(sqlUpd,parameters.toArray());
			
			StringBuilder sqlIns = new StringBuilder();
			sqlIns.append("insert into vivai_r_stato_domanda (id_domanda,id_stato_domanda,numero_protocollo,");
			sqlIns.append("data_protocollo,note_stato,data_inserimento,data_fine_stato,fk_config_utente,progressivo) ");
			sqlIns.append("values (?,?,?,?,?,?,?,?,");
			sqlIns.append("(SELECT COUNT(progressivo)+1 from vivai_r_stato_domanda  where id_domanda = ?) )");
			parameters = new ArrayList<>();
			parameters.add(statoDomanda.getIdDomanda());
			parameters.add(statoDomanda.getIdStatoDomanda());
			parameters.add(statoDomanda.getNumeroProtocollo());
			parameters.add(statoDomanda.getDataProtocollo());
			parameters.add(statoDomanda.getNoteStato());
			parameters.add(now);
			parameters.add(statoDomanda.getDataFineStato());
			parameters.add(statoDomanda.getFkConfigUtente());
			parameters.add(statoDomanda.getIdDomanda());
			return jdbcTemplate.update(sqlIns.toString(),parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_r_stato_domanda where id_domanda = ?";
			return jdbcTemplate.update(sql,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public RStatoDomanda getLastStato(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select * from vivai_r_stato_domanda vrsd " + 
				"where id_domanda = ? and data_fine_stato is null " + 
				"order by progressivo desc FETCH FIRST ROW ONLY";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," idDomanda: " + idDomanda));
			List<RStatoDomanda> res = jdbcTemplate.query(sql, new RStatoDomandaMapper(), idDomanda);
			if(res != null && res.size() > 0) {
				return res.get(0);
			}
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public RStatoDomanda getLastStatoByType(Integer idDomanda, StatoDomandaEnum statoDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select * from vivai_r_stato_domanda vrsd " + 
				"where id_domanda = ? and id_stato_domanda = ? " + 
				"order by progressivo desc FETCH FIRST ROW ONLY";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," idDomanda: " 
				+ idDomanda + " - statoDomanda: " + statoDomanda.getValue()));
			List<RStatoDomanda> res = jdbcTemplate.query(sql, new RStatoDomandaMapper(), idDomanda, statoDomanda.getValue());
			if(res != null && res.size() > 0) {
				return res.get(0);
			}
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(RStatoDomanda statoDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_r_stato_domanda ");
			sql.append("SET note_stato= ? ");
			sql.append("WHERE id_stato_domanda= ? AND id_domanda= ? AND progressivo= ? ");
			
			List<Object> params = new ArrayList<>();
			params.add(statoDomanda.getNoteStato());
			params.add(statoDomanda.getIdStatoDomanda());
			params.add(statoDomanda.getIdDomanda());
			params.add(statoDomanda.getProgressivo());
			
			return jdbcTemplate.update(sql.toString(),params.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateProtocolData(RStatoDomanda statoDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_r_stato_domanda ");
			sql.append("SET numero_protocollo = ?, data_protocollo = ? ");
			sql.append("WHERE id_stato_domanda= ? AND id_domanda= ? AND progressivo = ");
			sql.append("(select max(progressivo) from vivai_r_stato_domanda WHERE id_stato_domanda= ? AND id_domanda= ?)");
			
			List<Object> params = new ArrayList<>();
			params.add(statoDomanda.getNumeroProtocollo());
			params.add(statoDomanda.getDataProtocollo());
			params.add(statoDomanda.getIdStatoDomanda());
			params.add(statoDomanda.getIdDomanda());
			params.add(statoDomanda.getIdStatoDomanda());
			params.add(statoDomanda.getIdDomanda());
			
			return jdbcTemplate.update(sql.toString(),params.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	
}
