/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PfPg;

public class PfPgMapper implements RowMapper<PfPg> {

	@Override
	public PfPg mapRow(ResultSet rs, int arg1) throws SQLException {
		PfPg pfPg = new PfPg();		
		pfPg.setIdSoggettoPf(rs.getInt("id_soggetto_pf"));
		pfPg.setIdSoggettoPg(rs.getInt("id_soggetto_pg"));		
		pfPg.setDataInserimento(rs.getDate("data_inserimento"));
		pfPg.setDataFineValidita(rs.getDate("data_fine_validita"));
		pfPg.setFkConfigUtente(rs.getInt("fk_config_utente"));
		return pfPg;
	}

}
