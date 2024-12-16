/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import it.csi.vifo.vifoapi.dto.VoceDizionario;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VoceDizionarioMapper implements RowMapper<VoceDizionario> {

	@Override
	public VoceDizionario mapRow(ResultSet rs, int arg1) throws SQLException {
		VoceDizionario voceDizionario = new VoceDizionario();
		
		voceDizionario.setId(rs.getInt("id"));
		voceDizionario.setDescrizione(rs.getString("descrizione"));
		return voceDizionario;
	}
}
