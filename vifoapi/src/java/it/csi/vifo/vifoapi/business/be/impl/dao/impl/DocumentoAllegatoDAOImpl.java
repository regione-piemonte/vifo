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

import it.csi.vifo.vifoapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DocumentoAllegatoMapper;
import it.csi.vifo.vifoapi.dto.DocumentoAllegato;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class DocumentoAllegatoDAOImpl extends GenericDAO implements DocumentoAllegatoDAO {

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public int save(DocumentoAllegato documento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_allegato ");
			sql.append("(fk_domanda, fk_tipo_allegato, nome_file, estensione_file, dimensione_file_kb, data_upload, note,");
			sql.append(" fk_config_utente, uid_index, id_doc_doqui, id_classificazione_doqui) ");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(documento.getFkDomanda());
			parameters.add(documento.getFkTipoAllegato());
			parameters.add(documento.getNomeFile());
			parameters.add(documento.getEstensioneFile());
			parameters.add(documento.getDimensioneFileKb());
			parameters.add(now);
			parameters.add(documento.getNote());
			parameters.add(documento.getFkConfigUtente());
			parameters.add(documento.getUidIndex());
			parameters.add(documento.getIdDocDoqui());
			parameters.add(documento.getIdClassificazioneDoqui());
	
			return AIKeyHolderUtil.updateWithGenKey("id_allegato", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateUidIndex(DocumentoAllegato documento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "UPDATE vivai_t_allegato SET uid_index = ? WHERE id_allegato = ?";
			return jdbcTemplate.update(sql, documento.getUidIndex(),documento.getIdAllegato()) ;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<DocumentoAllegato> getByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select a.*, t.descr_tipo_allegato from vivai_t_allegato a "
					+ "inner join vivai_d_tipo_allegato t on a.fk_tipo_allegato = t.id_tipo_allegato "
					+ "where fk_domanda = ? order by id_allegato ";
			return jdbcTemplate.query(sql, new DocumentoAllegatoMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public DocumentoAllegato getByIdAllegato(Integer idAllegato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select a.*, t.descr_tipo_allegato from vivai_t_allegato a "
					+ "inner join vivai_d_tipo_allegato t on a.fk_tipo_allegato = t.id_tipo_allegato where id_allegato = ? ";
			return jdbcTemplate.queryForObject(sql, new DocumentoAllegatoMapper(), idAllegato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}



	@Override
	public int deleteByIdAllegato(Integer idAllegato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "DELETE FROM vivai_t_allegato WHERE id_allegato = ? ";
			return jdbcTemplate.update(sql, idAllegato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public int deleteByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "DELETE FROM vivai_t_allegato WHERE fk_domanda = ? ";
			return jdbcTemplate.update(sql, idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
