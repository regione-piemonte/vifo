/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.AllegatoDTO;

public class AllegatoDTOMapper implements RowMapper<AllegatoDTO> {

	@Override
	public AllegatoDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		AllegatoDTO item = new AllegatoDTO();
		item.setId(rs.getInt("id"));
		item.setIdPratica(rs.getInt("fk_domanda"));
		item.setIdTipoAllegato(rs.getInt("id_tipo_allegato"));
		item.setDescrizione(rs.getString("descrizione"));
		if(rs.getString("id_padre") != null) {
			item.setIdPadre(rs.getLong("id_padre"));
		}
		item.setTitolo(rs.getString("titolo"));
		item.setUidIndex(rs.getString("uid_index"));
		item.setCodiceCosmo(rs.getString("codice_cosmo"));
		item.setUploaduuidCosmo(rs.getString("uploaduuid_cosmo"));
		return item;
	}

}
