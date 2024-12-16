/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifoapi.dto.ConfigPagamenti;
import it.csi.vifo.vifoapi.dto.PdfTesto;
import it.csi.vifo.vifoapi.dto.enumeration.ConfCodiciPagamentoEnum;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.ParamsActaEnum;
import it.csi.vifo.vifoapi.util.TipoParametroApplEnum;

@Component
public class ConfigurationDAOImpl extends GenericDAO implements ConfigurationDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public String getConfigurationByTipoParametro(TipoParametroApplEnum tipoParametro) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select p.parametro_appl_char, p.parametro_appl_num from vivai_d_tipo_parametro_appl t " + 
					"inner join vivai_cnf_parametro_appl p on p.fk_tipo_parametro_appl = t.id_tipo_paremetro_appl " + 
					"where t.descr_tipo_parametro_appl = ? ";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"  sql: " + sql + " - tipoParametro: " + tipoParametro));
			return jdbcTemplate.query(sql, new Object[] {tipoParametro.toString()},  new ResultSetExtractor<String>()
	        {
		    	@Override
				public String extractData(ResultSet rs) throws SQLException {
		    		String res = null;
		    		if(rs.next()) {
		    			res = rs.getString("parametro_appl_num");
		    			if(!NumberUtils.isNumber(res)) {
		    				res = rs.getString("parametro_appl_char");
		    			}
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}	
	
	@Override
	public List<String> getListConfigurationsByTipoParametro(TipoParametroApplEnum tipoParametro) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select p.parametro_appl_char, p.parametro_appl_num from vivai_d_tipo_parametro_appl t " + 
					"inner join vivai_cnf_parametro_appl p on p.fk_tipo_parametro_appl = t.id_tipo_paremetro_appl " + 
					"where t.descr_tipo_parametro_appl = ?  order by id_parametro_appl";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"  sql: " + sql + " - tipoParametro: " + tipoParametro));
			return jdbcTemplate.query(sql, new Object[] {tipoParametro.toString() },  new ResultSetExtractor<List<String>>()
	        {
		    	@Override
				public List<String> extractData(ResultSet rs) throws SQLException {
		    		List<String> res = new ArrayList<String>();
		    		String value;
		    		while(rs.next()) {
		    			value = rs.getString("parametro_appl_num");
		    			if(!NumberUtils.isNumber(value)) {
		    				res.add(rs.getString("parametro_appl_char"));
		    			}else {
		    				res.add(value);
		    			}
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<PdfTesto> getListTestiByTipoParametro(TipoParametroApplEnum tipoParametro) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select p.parametro_appl_char, p.parametro_appl_num from vivai_d_tipo_parametro_appl t " + 
					"inner join vivai_cnf_parametro_appl p on p.fk_tipo_parametro_appl = t.id_tipo_paremetro_appl " + 
					"where t.descr_tipo_parametro_appl = ?  order by id_parametro_appl";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"  sql: " + sql + " - tipoParametro: " + tipoParametro));
			return jdbcTemplate.query(sql, new Object[] {tipoParametro.toString() },  new ResultSetExtractor<List<PdfTesto>>()
	        {
		    	@Override
				public List<PdfTesto> extractData(ResultSet rs) throws SQLException {
		    		List<PdfTesto> res = new ArrayList<PdfTesto>();
		    		String value;
		    		while(rs.next()) {
		    			value = rs.getString("parametro_appl_char");
		    			if(value.indexOf("<evidenziato giallo>") == 0) {
		    				res.add(new PdfTesto(value.substring(20),"EVIDENZIATO GIALLO"));
		    			}else if(value.indexOf("<evidenziato rosso>") == 0) {
		    				res.add(new PdfTesto(value.substring(19),"EVIDENZIATO ROSSO"));
		    			}else {
		    				res.add(new PdfTesto(value,""));
		    			}
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public ConfigPagamenti getConfigPagamentiBe() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select codice, valore from vivai_cnf_param_pagopa";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"  sql: " + sql));
			return jdbcTemplate.query(sql,  new ResultSetExtractor<ConfigPagamenti>()
	        {
		    	@Override
				public ConfigPagamenti extractData(ResultSet rs) throws SQLException {
		    		ConfigPagamenti res = new ConfigPagamenti();
		    		while(rs.next()) {
		    			switch(ConfCodiciPagamentoEnum.valueOf(rs.getString("codice"))) {
		    				case CODICE_PROCEDIMENTO:
		    					res.setIdProcedimento(rs.getLong("valore"));
		    					break;		    				
		    				case WEB_SERVICE_URL:
		    					res.setWebServiceUrl(rs.getString("valore"));
		    					break;
		    				case WEB_SERVICE_USER:
		    					res.setWebServiceUser(rs.getString("valore"));
		    					break;
		    				case WEB_SERVICE_PWD:
		    					res.setWebServicePwd(rs.getString("valore"));
		    					break;	
		    				case IPS_ENABLED_FOR_CALLBACK_RICEVI_RT:
		    					res.setWebServicePwd(rs.getString("valore"));
		    					break;	
			    			default:
			                    break;	
		    			}
		    			
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public ConfigPagamenti getConfigPagamentiFe() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select codice, valore from vivai_cnf_param_pagopa";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"  sql: " + sql));
			return jdbcTemplate.query(sql,  new ResultSetExtractor<ConfigPagamenti>()
	        {
		    	@Override
				public ConfigPagamenti extractData(ResultSet rs) throws SQLException {
		    		ConfigPagamenti res = new ConfigPagamenti();
		    		while(rs.next()) {
		    			switch(ConfCodiciPagamentoEnum.valueOf(rs.getString("codice"))) {
		    				case WEB_APP_URL:
		    					res.setWebAppUrl(rs.getString("valore"));
		    					break;
		    				case CODICE_PROCEDIMENTO:
		    					res.setIdProcedimento(rs.getLong("valore"));
		    					break;
		    				case CODICE_PAGAMENTO_CORRISPETTIVO:
		    					res.setCodicePagamentoCorrispettivo(rs.getString("valore"));
		    					break;
		    				case CODICE_PAGAMENTO_FATTURA:
		    					res.setCodicePagamentoFattura(rs.getString("valore"));
		    					break;
		    				case RUOLO_IRIDE:
		    					res.setRuoloIride(rs.getString("valore"));
		    					break;
		    				case LIVELLO_IRIDE:
		    					res.setLivelloIride(rs.getInt("valore"));
		    					break;		    				
			    			default:
			                    break;	
		    			}
		    			
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public String getParamActaByCode(ParamsActaEnum code) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select valore from vivai_cnf_param_acta where codice = ? ";
			return jdbcTemplate.queryForObject(sql, String.class, code.toString());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
