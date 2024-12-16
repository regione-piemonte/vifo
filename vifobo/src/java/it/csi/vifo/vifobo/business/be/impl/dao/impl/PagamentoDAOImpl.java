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

import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.PagamentoMapper;
import it.csi.vifo.vifobo.dto.Pagamento;
import it.csi.vifo.vifobo.util.AIKeyHolderUtil;
import it.csi.vifo.vifobo.util.CodicePagamentoEnum;
import it.csi.vifo.vifobo.util.GenericDAO;
import it.csi.vifo.vifobo.util.TipoPagamentoEnum;

@Component
public class PagamentoDAOImpl extends GenericDAO implements PagamentoDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int insert(Pagamento pagamento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_pagamento "); 
			sql.append("(fk_domanda, fk_tipo_pagamento, flg_fattura, data_inserimento, fk_config_utente) "); 
			sql.append("VALUES(?, ?, ?, ?, ?) "); 
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(pagamento.getFkDomanda());
			parameters.add(pagamento.getFkTipoPagamento());
			parameters.add(pagamento.getFlgFattura());
			parameters.add(now);
			parameters.add(pagamento.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_pagamento", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateLastByIdDomandaAndTipo(Pagamento pagamento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_pagamento "); 
			sql.append("SET iuv = ?, stato_pagamento = ?, data_aggiornamento= ?, esito = ? "); 
			sql.append("WHERE id_pagamento = ( "); 
			sql.append("select max(id_pagamento) from vivai_t_pagamento  "); 
			sql.append("WHERE fk_domanda = ? and fk_tipo_pagamento = ?) "); 
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(pagamento.getIuv());
			parameters.add(pagamento.getStatoPagamento() != null?pagamento.getStatoPagamento().getDescrizione():null);
			parameters.add(now);
			parameters.add(pagamento.getEsito());
			parameters.add(pagamento.getFkDomanda());	
			parameters.add(pagamento.getFkTipoPagamento());	
	
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateByIuvIfStatoPagamentoChanged(String iuv, CodicePagamentoEnum statoPagamento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE vivai_t_pagamento "); 
				sql.append("SET stato_pagamento=?, data_aggiornamento= ? "); 
				sql.append("WHERE iuv = ? and stato_pagamento <> ? "); 
				
				Timestamp now = new Timestamp(System.currentTimeMillis());
				List<Object> parameters = new ArrayList<>();
				parameters.add(statoPagamento.getDescrizione());
				parameters.add(now);
				parameters.add(iuv);
				parameters.add(statoPagamento.getDescrizione());
				
				return jdbcTemplate.update(sql.toString(), parameters.toArray());
	       
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Pagamento> getPagamentiByIdDomandaAndTipo(int idDomanda, TipoPagamentoEnum tipo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id_pagamento, fk_domanda, fk_tipo_pagamento, flg_fattura, iuv, "); 
			sql.append("esito, stato_pagamento, data_inserimento, data_aggiornamento, fk_config_utente "); 
			sql.append("from vivai_t_pagamento ");
			sql.append("where fk_domanda = ? and fk_tipo_pagamento = ? ");		
			sql.append("order by id_pagamento desc ");
	
			return jdbcTemplate.query(sql.toString(), new PagamentoMapper(), idDomanda, tipo.getValue());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deletePagamentoPendingByIdDomanda(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("Delete from vivai_t_pagamento ");
			sql.append("where fk_domanda = ? and iuv is null ");
	
			return jdbcTemplate.update(sql.toString(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
