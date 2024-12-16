/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.SpecieRichiesta;

public class SpecieRichiestaMapper implements RowMapper<SpecieRichiesta> {

	@Override
	public SpecieRichiesta mapRow(ResultSet rs, int arg1) throws SQLException {
		SpecieRichiesta specieRichiesta = new SpecieRichiesta();
		
		specieRichiesta.setIdDomanda(rs.getInt("id_domanda"));
		specieRichiesta.setIdSpecie(rs.getInt("id_specie"));
		specieRichiesta.setQtaRichiesta(rs.getInt("qta_richiesta"));
		specieRichiesta.setNomeComune(rs.getString("nome_comune"));
		specieRichiesta.setNomeScientifico(rs.getString("nome_scientifico"));
		specieRichiesta.setIdDomanda(rs.getInt("fk_config_utente"));
		
		return specieRichiesta;
	}
}
