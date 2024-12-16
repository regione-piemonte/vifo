/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.dto.BoProcLog;

public class BoProcLogMapper implements RowMapper<BoProcLog> {

		@Override
		public BoProcLog mapRow(ResultSet rs, int arg1) throws SQLException {
			BoProcLog log = new BoProcLog();
			log.setIdCnfBoprocLog(rs.getInt("id_cnf_boproc_log"));
			log.setFkPratica(rs.getString("fk_pratica"));
			log.setDataInizio(rs.getDate("data_inizio"));
			log.setDataFine(rs.getDate("data_fine"));
			log.setFkStepBoproc(rs.getInt("fk_step_boproc"));
			log.setEsito(rs.getString("esito"));
			log.setCountTentativo(rs.getInt("count_tentativo"));
			log.setCodErroreBoproc(rs.getString("cod_errore_boproc"));
			log.setNoteErroreBoproc(rs.getString("note_errore_boproc"));
			log.setJsonBoproc(rs.getString("json_boproc"));
			return log;
		}
}
