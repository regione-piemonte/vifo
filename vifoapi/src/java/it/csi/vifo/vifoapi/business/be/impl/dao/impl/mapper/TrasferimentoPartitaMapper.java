/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.TrasferimentoPartita;

public class TrasferimentoPartitaMapper extends BaseMapper implements RowMapper<TrasferimentoPartita> {
	
	private static final SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public TrasferimentoPartita mapRow(ResultSet rs, int arg1) throws SQLException {
		TrasferimentoPartita partita = new TrasferimentoPartita();
		
		partita.setIdPartita(getInteger(rs,"id_partita"));
		partita.setFkPartitaOriginaria(getInteger(rs,"fk_partita_orig"));
		partita.setSpecie(rs.getString("specie"));
		String specieLatino = rs.getString("specie_latino");
		partita.setSpecie(partita.getSpecie() + (specieLatino == null?"":" - " + specieLatino));
		partita.setVivaioDestinazioneDescr(rs.getString("vivaio_destinazione"));
		partita.setVivaioOrigineDescr(rs.getString("vivaio_origine"));
		partita.setFkVivaioDestinazione(rs.getInt("fk_vivaio_destinazione"));
		partita.setFkVivaioOrigine(rs.getInt("fk_vivaio_origine"));
		partita.setDataNotifica(sf.format(rs.getDate("data_ins")));
		partita.setQtaTrasferita(rs.getInt("qta_carico"));
		partita.setFlgTipoTrasferimento(rs.getString("flg_tipo_trasferimento"));
		partita.setFlgValidTrasferimento(rs.getString("flg_valid_trasf"));
		partita.setFkConfUtente(rs.getInt("id_config_utente"));
		partita.setFkVivaioUtente(getInteger(rs,"vivaio_utente"));
		
		return partita;
	}
}
