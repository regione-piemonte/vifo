/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.BaseDdt;
import it.csi.vifo.vifoapi.util.DateUtil;

public class BaseDdtMapper implements RowMapper<BaseDdt>{

	@Override
	public BaseDdt mapRow(ResultSet rs, int arg1) throws SQLException {
		BaseDdt infoDdt = new BaseDdt();
		infoDdt.setNumeroDdt(rs.getInt("numero_ddt"));
		infoDdt.setAnnoDdt(rs.getInt("anno_ddt"));
		infoDdt.setDataCreazione(DateUtil.dateFormatter.format(rs.getDate("data_inserimento")));
		infoDdt.setQtaDdt(rs.getInt("qta_ddt"));
		return infoDdt;
		
	}

}
