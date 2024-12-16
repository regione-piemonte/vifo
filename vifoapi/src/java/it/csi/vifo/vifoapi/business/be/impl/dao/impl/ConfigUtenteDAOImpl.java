/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.ConfigUtenteMapper;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.ListUtil;

@Component
public class ConfigUtenteDAOImpl extends GenericDAO implements ConfigUtenteDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public ConfigUtente getConfigUtenteById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_cnf_config_utente cu WHERE cu.id_config_utente = ? ";
			
			List<ConfigUtente> cnft = jdbcTemplate.query(sql, new ConfigUtenteMapper(), id);
			if (ListUtil.isEmpty(cnft)) {
				return null;
			} else {
				return cnft.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public ConfigUtente getConfigUtenteBySoggettoId(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_cnf_config_utente cu WHERE cu.fk_soggetto = ?";
			List<ConfigUtente> cnft = jdbcTemplate.query(sql, new ConfigUtenteMapper(), id);
			if (ListUtil.isEmpty(cnft)) {
				return null;
			} else {
				return cnft.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

	@Override
	public int createConfigUtente(ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO vivai_cnf_config_utente(");
			sb.append(
					"fk_tipo_profilo, nr_accessi, data_primo_accesso, data_ultimo_accesso, flg_trattamento_dati_personali, fk_soggetto, fk_vivaio)");
			sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(configUtente.getFkTipoProfilo());
			parameters.add(configUtente.getNrAccessi());
			parameters.add(configUtente.getDataPrimoAccesso());
			parameters.add(configUtente.getDataUltimoAccesso());
			parameters.add(configUtente.getFlgTrattamentoDatiPersonali());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkVivaio());
	
			return AIKeyHolderUtil.updateWithGenKey("id_config_utente", sb.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void updateConfigUtente(ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_cnf_config_utente SET");
			sql.append(" fk_tipo_profilo = ?, nr_accessi = ?, fk_vivaio = ? ");
			sql.append(", data_primo_accesso = ?, data_ultimo_accesso = ?, flg_trattamento_dati_personali = ?, fk_soggetto = ?");
			sql.append(" where id_config_utente = ?");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(configUtente.getFkTipoProfilo());
			parameters.add(configUtente.getNrAccessi());
			parameters.add(configUtente.getFkVivaio());
			parameters.add(configUtente.getDataPrimoAccesso());
			parameters.add(configUtente.getDataUltimoAccesso());
			parameters.add(configUtente.getFlgTrattamentoDatiPersonali());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getIdConfigUtente());
	
			jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<ConfigUtente> getConfigUtenteBySoggettoIdAnProfilo(int id,int profilo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_cnf_config_utente cu WHERE cu.fk_soggetto = ? AND cu.fk_tipo_profilo=? ";
			List<ConfigUtente> cnft = jdbcTemplate.query(sql, new ConfigUtenteMapper(), id,profilo);
			if (ListUtil.isEmpty(cnft)) {
				return null;
			} else {
				return cnft;
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	@Override
	public List<ConfigUtente> getConfUtenteBySoggIdAnProfOrderByDate(int id,int profilo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_cnf_config_utente cu WHERE cu.fk_soggetto = ? AND cu.fk_tipo_profilo=? ORDER BY cu.data_ultimo_accesso desc ";
			List<ConfigUtente> cnft = jdbcTemplate.query(sql, new ConfigUtenteMapper(), id,profilo);
			
			if (ListUtil.isEmpty(cnft)) {
				return null;
			} else {
				return cnft;
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
}