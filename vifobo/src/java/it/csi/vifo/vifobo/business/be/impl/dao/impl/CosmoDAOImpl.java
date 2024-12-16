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

import it.csi.vifo.vifobo.business.be.impl.dao.CosmoDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.AllegatoDTOMapper;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.BoProcLogMapper;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.MetadatiCosmoMapper;
import it.csi.vifo.vifobo.dto.AllegatoDTO;
import it.csi.vifo.vifobo.dto.BoProcLog;
import it.csi.vifo.vifobo.dto.MetadatiCosmo;
import it.csi.vifo.vifobo.util.AIKeyHolderUtil;
import it.csi.vifo.vifobo.util.GenericDAO;

@Component
public class CosmoDAOImpl extends GenericDAO implements CosmoDAO {

	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public int insertBoprocLog(String idPratica, int step) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select COUNT(fk_pratica) from vivai_cnf_boproc_log where fk_step_boproc = ? and fk_pratica = ?");
			int res = jdbcTemplate.queryForObject(sql.toString(), Integer.class,step, idPratica);
			if(res > 0) {
				return res;
			}
			
			sql = new StringBuilder();
			sql.append("INSERT INTO vivai_cnf_boproc_log ");
			sql.append("(fk_pratica, data_inizio, fk_step_boproc, count_tentativo)");
			sql.append(" VALUES (?, ?, ?, 0)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object>parameters = new ArrayList<>();
			parameters.add(idPratica);
			parameters.add(now);
			parameters.add(step);
			
			return AIKeyHolderUtil.updateWithGenKey("id_cnf_boproc_log", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<BoProcLog> getProcToWorkByStep(int step) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select * from vivai_cnf_boproc_log ");
			sql.append("where fk_step_boproc = ? and data_fine is null ");
			
			return jdbcTemplate.query(sql.toString(), new BoProcLogMapper(), step);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int getCountErrore(String idPratica) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT count_tentativo from vivai_cnf_boproc_log ");
			sql.append("where fk_pratica = ? and data_fine is null ");
			
			return jdbcTemplate.queryForObject(sql.toString(), Integer.class, idPratica);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateBoprocLog(String idPratica, int countTentativo, Integer codErrore, String noteErrore,
			String json) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> params = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder();
			sql.append("update vivai_cnf_boproc_log set esito = 'KO', note_errore_boproc = ?, ");
			sql.append("cod_errore_boproc = ?, count_tentativo = ? ");
			
			params.add(noteErrore);
			params.add(codErrore);
			params.add(countTentativo);
			
			if (json != null) {
				sql.append(",json_boproc = to_json(?) ");
				params.add(json);
			}
			
			sql.append(" where fk_pratica = ? and data_fine is null ");
			params.add(idPratica);		
			
			return jdbcTemplate.update(sql.toString(), params.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int storicizzaBoprocLog(String idPratica, String json) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> params = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder();
			sql.append("update vivai_cnf_boproc_log set data_fine = date_trunc('second', now()), esito = 'OK' ");
			if (json != null) {
				sql.append(",json_boproc = to_json(?) ");
				params.add(json);
			}
			sql.append(" where fk_pratica = ? and data_fine is null ");
			params.add(idPratica);		
			
			return jdbcTemplate.update(sql.toString(), params.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int storicizzaBoprocLogNoData(String idPratica, String json) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> params = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder();
			sql.append("update vivai_cnf_boproc_log set esito = 'OK'");
			if (json != null) {
				sql.append(",json_boproc = to_json(?) ");
				sql.append(json);
			}
			sql.append(" where fk_pratica = ? and data_fine is null ");
			params.add(idPratica);		
			
			return jdbcTemplate.update(sql.toString(), params.toArray());	
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public boolean startSemaforo(String codiceSemaforo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_cnf_semaforo SET valore = 1 WHERE codice = ? AND valore = 0");
			
			return jdbcTemplate.update(sql.toString(), codiceSemaforo) > 0;	
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void stopSemaforo(String codiceSemaforo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_cnf_semaforo SET valore = 0 WHERE codice = ? ");
			
			jdbcTemplate.update(sql.toString(), codiceSemaforo);	
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public boolean isScheduleActive() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append("case ");
			sb.append("when exists (select 1 ");
			sb.append("from ");
			sb.append("(select icpa.valore as ora ");
			sb.append("from vivai_cnf_param_acta icpa ");
			sb.append("where icpa.codice = 'ORARIO_INIZIO_BOPROC') da, ");
			sb.append("(select icpa.valore as ora ");
			sb.append("from vivai_cnf_param_acta icpa ");
			sb.append("where icpa.codice = 'ORARIO_FINE_BOPROC') a ");
			sb.append("where now()::time between  da.ora::time and a.ora::time");
			sb.append(") ");
			sb.append("then TRUE ");
			sb.append("else FALSE ");
			sb.append("end");

			boolean result = queryForObject(sb.toString(), Boolean.class);
			
			return result;
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public MetadatiCosmo getMetadatiByIdDomanda(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from v_mtd_cosmo where id_domanda = ? ");
			
			return jdbcTemplate.queryForObject(sql.toString(), new MetadatiCosmoMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<AllegatoDTO> getElencoAllegati(Integer idDomanda, Integer inviato, boolean isDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct ");
			sql.append("vta.id_allegato as id, ");
			sql.append("vta.fk_domanda, uploaduuid_cosmo, ");
			sql.append("vta.fk_tipo_allegato as id_tipo_allegato, ");
			sql.append("idta.descr_tipo_allegato as descrizione, ");
			sql.append("idta.flg_documento_padre, ");
			sql.append("case when idta.flg_documento_padre = 1 then null ");
			sql.append("	when idta.flg_documento_padre = 0 then tab1.id_allegato ");
			sql.append("	end as id_padre, ");
			sql.append("case when (vta.fk_tipo_allegato)+(tab1.fk_tipo_allegato) = 25 then 'doc_identita_aa'  ");
			sql.append("	 when (vta.fk_tipo_allegato)+(tab1.fk_tipo_allegato) = 16 then 'doc_identita_dga' ");
			sql.append("else idta.codice_cosmo end as codice_cosmo, ");
			sql.append("vta.nome_file as titolo, ");
			sql.append("vta.dimensione_file_kb, ");
			sql.append("vta.uid_index ");
			sql.append("from  ");
			sql.append("vivai_t_allegato vta  ");
			sql.append("join vivai_d_tipo_allegato idta on vta.fk_tipo_allegato = idta.id_tipo_allegato  ");
			sql.append("left join ( ");
			sql.append("	select  ");
			sql.append("	vta2.id_allegato, ");
			sql.append("	vta2.fk_tipo_allegato, ");
			sql.append("	vta2.fk_domanda ");
			sql.append("	from vivai_t_allegato vta2  ");
			sql.append("	join vivai_d_tipo_allegato idta1 on vta2.fk_tipo_allegato = idta1.id_tipo_allegato  ");
			sql.append("	where idta1.flg_documento_padre = 1 and vta2.flg_invio_cosmo = ?) as tab1 on vta.fk_domanda  = tab1.fk_domanda ");
			sql.append("where vta.fk_domanda = ? ");
			if(isDomanda) {
				sql.append("and vta.fk_tipo_allegato not in (7,8,9,10,11,12) ");
			}else {
				sql.append("and vta.fk_tipo_allegato in (7,8,9,10,11,12,13) ");
			}
			sql.append("and idta.flg_attivo = 1 ");
			sql.append("and idta.codice_cosmo is not null ");
			sql.append("and vta.flg_invio_cosmo = ? ");
			sql.append("ORDER BY ID_PADRE NULLS FIRST ");
			
			return jdbcTemplate.query(sql.toString(), new AllegatoDTOMapper(), inviato, idDomanda, inviato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateDocumentoWithUuidCosmo(long idDocumentoAllegato, String uuidCosmo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_allegato set flg_invio_cosmo = 1,");
			sql.append("UPLOADUUID_COSMO = ? ");
			sql.append("WHERE id_allegato = ? ");
			
			return jdbcTemplate.update(sql.toString(), uuidCosmo, idDocumentoAllegato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateDocumentoWithInvoCosmoTo2(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_allegato set flg_invio_cosmo = 2");
			sql.append("WHERE fk_domanda = ? and flg_invio_cosmo = 1 ");
			
			return jdbcTemplate.update(sql.toString(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateDocumentoWithInvoActa(Integer idAllegato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_allegato set flg_invio_acta = 1");
			sql.append("WHERE id_allegato = ? ");
			
			return jdbcTemplate.update(sql.toString(), idAllegato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public boolean isPraticaClosed(String idPratica) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = new StringBuilder();
			sql.append("select COUNT(fk_pratica) from vivai_cnf_boproc_log ");
			sql.append("where fk_pratica = ? and fk_step_boproc = 5 and data_fine is not null ");
			
			return jdbcTemplate.queryForObject(sql.toString(), Integer.class, idPratica) > 0;
			

		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	

}
