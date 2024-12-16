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

import it.csi.vifo.vifoapi.business.be.impl.dao.FotoSpecieDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.FotoSpecieMapper;
import it.csi.vifo.vifoapi.dto.FotoSpecie;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class FotoSpecieDAOImpl extends GenericDAO implements FotoSpecieDAO {

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public int insert(FotoSpecie item) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_foto_specie ");
			sql.append(" (fk_tipo_foto, fk_specie, nome_file, estensione_file, dimensione_file_kb,");
			sql.append(" data_upload, uid_index, note, fk_config_utente) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?)");
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString()));
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(item.getFkTipoFoto());
			parameters.add(item.getFkSpecie());
			parameters.add(item.getNomeFile());
			parameters.add(item.getEstensioneFile());
			parameters.add(item.getDimensioneFileKb());
			parameters.add(now);
			parameters.add(item.getUidIndex());
			parameters.add(item.getNote());
			parameters.add(item.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_foto_specie", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}

	}

	@Override
	public int update(FotoSpecie item) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_foto_specie "); 
			sql.append("SET fk_tipo_foto=?, fk_specie=?, nome_file=?, estensione_file=?,");
			sql.append("dimensione_file_kb=?, data_upload=?, uid_index=?, note=?, fk_config_utente=? ");
			sql.append("WHERE id_foto_specie=?");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(item.getFkTipoFoto());
			parameters.add(item.getFkSpecie());
			parameters.add(item.getNomeFile());
			parameters.add(item.getEstensioneFile());
			parameters.add(item.getDimensioneFileKb());
			parameters.add(now);
			parameters.add(item.getUidIndex());
			parameters.add(item.getNote());
			parameters.add(item.getFkConfigUtente());
			parameters.add(item.getIdFotoSpecie());
			return jdbcTemplate.update(sql.toString(),parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateUidIndex(FotoSpecie item) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "UPDATE vivai_t_foto_specie SET uid_index = ? WHERE id_foto_specie = ?";
			return jdbcTemplate.update(sql, item.getUidIndex(),item.getIdFotoSpecie()) ;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteById(int idFotoSpecie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_foto_specie where id_foto_specie = ?";
			return jdbcTemplate.update(sql,idFotoSpecie);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public FotoSpecie findByIdSpecieAndType(int idSpecie, int idType) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select * from vivai_t_foto_specie where fk_specie = ?  and fk_tipo_foto = ?";
			List<FotoSpecie> listFotoSpecie = jdbcTemplate.query(sql, new FotoSpecieMapper(), idSpecie, idType);
			return listFotoSpecie != null && listFotoSpecie.size() > 0?listFotoSpecie.get(0):null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
