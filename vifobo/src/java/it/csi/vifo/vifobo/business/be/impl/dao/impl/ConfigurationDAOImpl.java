/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifobo.dto.ConfigPagamenti;
import it.csi.vifo.vifobo.dto.PdfTesto;
import it.csi.vifo.vifobo.dto.enumeration.ConfCodiciPagamentoEnum;
import it.csi.vifo.vifobo.util.GenericDAO;
import it.csi.vifo.vifobo.util.ParamsActaEnum;
import it.csi.vifo.vifobo.util.TipoParametroApplEnum;

@Component
public class ConfigurationDAOImpl extends GenericDAO implements ConfigurationDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	
	
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
