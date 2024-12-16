/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.ConfigUtente;

public class ConfigUtenteExtendMapper implements RowMapper<ConfigUtente> {

	@Override
	public ConfigUtente mapRow(ResultSet rs, int arg1) throws SQLException {
		ConfigUtente configUtente = new ConfigUtente();
		
		configUtente.setIdConfigUtente(rs.getInt("id_config_utente"));
		configUtente.setFkTipoProfilo(rs.getInt("fk_tipo_profilo"));
		configUtente.setFkVivaio(rs.getInt("fk_Vivaio"));
		configUtente.setNrAccessi(rs.getInt("nr_accessi"));
		configUtente.setDataPrimoAccesso(rs.getTimestamp("data_primo_accesso"));
		configUtente.setDataUltimoAccesso(rs.getTimestamp("data_ultimo_accesso"));
		configUtente.setFlgTrattamentoDatiPersonali(rs.getByte("flg_trattamento_dati_personali"));
		configUtente.setFkSoggetto(rs.getInt("fk_soggetto"));
		configUtente.setFkProcedura(rs.getInt("fk_procedura"));
		return configUtente;
	}

}

