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
import it.csi.vifo.vifoapi.dto.SoggettoResultSearch;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

public class SoggettoResultSearchMapper implements RowMapper<SoggettoResultSearch>{

	@Override
	public SoggettoResultSearch mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SoggettoResultSearch sog = new SoggettoResultSearch();
		sog.setIdSoggetto(rs.getInt("id_soggetto"));
		sog.setCognomeRagSociale(rs.getString("nome"));
		sog.setCfPiva(rs.getString("cf_piva"));
		String istatComune = rs.getString("istat_comune");
		if(WsServiceImpl.mapComuniPerIstat != null) {
			Comune comune = WsServiceImpl.mapComuniPerIstat.get(rs.getString("istat_comune"));
			sog.setComune(comune == null?istatComune:comune.getNome());
		}
		sog.setTelefono(rs.getString("telefono"));
		sog.setMail(rs.getString("mail"));
		sog.setNumDomande(rs.getInt("num_domande"));
		return sog;
	}

}
