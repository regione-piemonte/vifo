/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioDAO;
import it.csi.vifo.vifoapi.dto.Inventario;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class InventarioDAOImpl extends GenericDAO implements InventarioDAO{

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public int insert(Inventario inventario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_inventario "); 
			sql.append("(fk_provenienza, data_carico, data_inserimento, fk_config_utente) "); 
			sql.append("VALUES(?, ?, ?, ?)");
	
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(inventario.getFkProventienza());		
			parameters.add(inventario.getDataCarico() == null? now :
					new Date(inventario.getDataCarico().getTime()));
			parameters.add(now);
			parameters.add(inventario.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_inventario", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(Inventario inventario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_inventario "); 
			sql.append("SET fk_provenienza= ?, data_aggiornamento= ?, fk_config_utente= ? ");
			
			parameters.add(inventario.getFkProventienza());	
			parameters.add(now);
			parameters.add(inventario.getFkConfigUtente());
			
			if(inventario.getDataCarico() != null) {
				sql.append(",data_carico= ? ");				
				parameters.add(inventario.getDataCarico());
			}			
			
			sql.append("WHERE id_inventario= ?");
			parameters.add(inventario.getIdInventario());
			
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteById(Integer id) {		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        String sql = "delete from vivai_t_inventario where id_inventario = ?";
	        return jdbcTemplate.update(sql,id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
