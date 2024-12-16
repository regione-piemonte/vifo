/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.FotoSpecie;

public class FotoSpecieMapper implements RowMapper<FotoSpecie>{

	@Override
	public FotoSpecie mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FotoSpecie fotoSpecie = new FotoSpecie();
		fotoSpecie.setIdFotoSpecie(rs.getInt("id_foto_specie"));
		fotoSpecie.setFkTipoFoto(rs.getInt("fk_tipo_foto"));
		fotoSpecie.setFkSpecie(rs.getInt("fk_specie"));
		fotoSpecie.setNomeFile(rs.getString("nome_file"));
		fotoSpecie.setEstensioneFile(rs.getString("estensione_file"));
		fotoSpecie.setDimensioneFileKb(rs.getLong("dimensione_file_kb"));
		fotoSpecie.setDataUpload(rs.getDate("data_upload"));
		fotoSpecie.setUidIndex(rs.getString("uid_index"));
		fotoSpecie.setNote(rs.getString("note"));
		fotoSpecie.setFkConfigUtente(rs.getInt("fk_config_utente"));
		return fotoSpecie;
		
	}

}
