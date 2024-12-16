/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifoapi.dto.Destinazione;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

public class DestinazioneMapper implements RowMapper<Destinazione> {

	@Override
	public Destinazione mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Destinazione destinazione = new Destinazione();
		
		destinazione.setIdCatasto(rs.getInt("id_catasto"));
		destinazione.setFkDomanda(rs.getInt("fk_domanda"));
		destinazione.setIstatComune(rs.getString("istat_comune"));
		Comune comune = WsServiceImpl.mapComuniPiemontePerIstat.get(rs.getString("istat_comune"));
		if(comune != null) {
			destinazione.setNomeComune(comune.getNome());
		}else {
			destinazione.setNomeComune("-");
		}
		destinazione.setSezione(rs.getString("sezione"));
		destinazione.setFoglio(rs.getInt("foglio"));
		destinazione.setParticella(rs.getString("particella"));
		String val = rs.getString("metri_lineari");
		if(val != null) {
			destinazione.setMetriLineari(rs.getDouble("metri_lineari"));
		}val = rs.getString("metri_quadri");
		if(val != null) {
			destinazione.setMetriQuadri(rs.getDouble("metri_quadri"));
		}
		destinazione.setDataInserimento(rs.getDate("data_inserimento"));
		destinazione.setDataAggiornamento(rs.getDate("data_aggiornamento"));
		destinazione.setFkConfigUtente(rs.getInt("fk_config_utente"));
		return destinazione;
		
	}
	
}