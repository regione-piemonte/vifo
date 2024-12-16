/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DoubleMapper implements RowMapper<Double> {

	@Override
	public Double mapRow(ResultSet rs, int arg1) throws SQLException {
		return rs.getDouble(1);
	}

}
