/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.dto.DocumentoAllegato;

public class DocumentoAllegatoMapper implements RowMapper<DocumentoAllegato> {

	@Override
	public DocumentoAllegato mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DocumentoAllegato documento = new DocumentoAllegato();
		
		documento.setIdAllegato(rs.getInt("id_allegato"));
		documento.setFkDomanda(rs.getInt("fk_domanda"));
		documento.setFkTipoAllegato(rs.getInt("fk_tipo_allegato"));
		documento.setTipoAllegatoDescr(rs.getString("descr_tipo_allegato"));
		documento.setNomeFile(rs.getString("nome_file"));
		documento.setEstensioneFile(rs.getString("estensione_file"));
		documento.setDimensioneFileKb(rs.getLong("dimensione_file_kb"));
		documento.setDataUpload(rs.getDate("data_upload"));
		documento.setFkConfigUtente(rs.getInt("fk_config_utente"));
		documento.setNote(rs.getString("note"));
		documento.setUidIndex(rs.getString("uid_index"));
		documento.setIdDocDoqui(rs.getString("id_doc_doqui"));
		documento.setIdClassificazioneDoqui(rs.getString("id_classificazione_doqui"));
		return documento;
	}

}
