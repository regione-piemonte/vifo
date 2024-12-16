/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.InfoNuovaAnnataInventario;

public class InfoNuovaAnnataInventarioMapper implements RowMapper<InfoNuovaAnnataInventario>{

	@Override
	public InfoNuovaAnnataInventario mapRow(ResultSet rs, int arg1) throws SQLException {
		InfoNuovaAnnataInventario info = new InfoNuovaAnnataInventario();
		info.setIdVivaio(rs.getInt("id_vivaio"));
		info.setVivaioDescr(rs.getString("nome_vivaio"));
		info.setNumPartitePresenti(rs.getInt("totali"));
		info.setNumPartiteNotEmpty(rs.getInt("disponibili"));
		info.setNumPartiteToCreate(info.getNumPartiteNotEmpty());
		return info;
	}

}
