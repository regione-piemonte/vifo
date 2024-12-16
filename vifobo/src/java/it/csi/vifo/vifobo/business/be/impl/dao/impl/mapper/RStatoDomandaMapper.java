/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.dto.RStatoDomanda;

public class RStatoDomandaMapper implements RowMapper<RStatoDomanda>{

	@Override
	public RStatoDomanda mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RStatoDomanda stato = new RStatoDomanda();
		stato.setIdStatoDomanda(rs.getInt("id_stato_domanda"));
		stato.setIdDomanda(rs.getInt("id_domanda"));
		stato.setProgressivo(rs.getInt("progressivo"));
		stato.setNumeroProtocollo(rs.getString("numero_protocollo"));
		stato.setDataProtocollo(rs.getDate("data_protocollo"));
		stato.setNoteStato(rs.getString("note_stato"));
		stato.setDataInserimento(rs.getDate("data_inserimento"));
		stato.setDataFineStato(rs.getDate("data_fine_stato"));
		stato.setFkConfigUtente(rs.getInt("fk_config_utente"));
		return stato;
	}
}
