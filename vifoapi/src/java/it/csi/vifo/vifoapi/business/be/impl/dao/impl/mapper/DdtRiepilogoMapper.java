/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.DdtRiepilogo;
import it.csi.vifo.vifoapi.util.DateUtil;

public class DdtRiepilogoMapper implements RowMapper<DdtRiepilogo> {

	@Override
	public DdtRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {
		DdtRiepilogo infoDdt = new DdtRiepilogo();
		infoDdt.setIdDomanda(rs.getInt("fk_domanda"));
		infoDdt.setNumeroDdt(rs.getInt("numero_ddt"));
		infoDdt.setAnnoDdt(rs.getInt("anno_ddt"));
		infoDdt.setDataCreazione(DateUtil.dateFormatter.format(rs.getDate("data_inserimento")));
		infoDdt.setIdConfUtende(rs.getInt("fk_config_utente"));
		infoDdt.setQtaDdt(rs.getInt("qta_ddt"));
		return infoDdt;
	}

}
