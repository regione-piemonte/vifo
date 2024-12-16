/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class StringMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int arg1) throws SQLException {
		return rs.getString(1);
	}

}
