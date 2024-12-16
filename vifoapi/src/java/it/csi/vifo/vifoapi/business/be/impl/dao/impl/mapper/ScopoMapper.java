/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Scopo;

public class ScopoMapper implements RowMapper<Scopo>{

	@Override
	public Scopo mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Scopo scopo = new Scopo();
		scopo.setIdScopo(rs.getInt("id_scopo"));
		scopo.setDescrizione(rs.getString("descr_scopo"));
		return scopo;
	}

}
