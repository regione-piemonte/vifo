/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Vivaio;

public class VivaioMapper implements RowMapper<Vivaio> {

	@Override
	public Vivaio mapRow(ResultSet rs, int arg1) throws SQLException {
		Vivaio vivaio = new Vivaio();
		
		vivaio.setIdVivaio(rs.getInt("id_Vivaio"));
		vivaio.setSigla(rs.getString("sigla"));
		vivaio.setNome(rs.getString("nome"));
		vivaio.setIndirizzo(rs.getString("indirizzo"));
		vivaio.setCap(rs.getString("cap"));
		vivaio.setIstatComune(rs.getString("istat_comune"));
		vivaio.setTelefono(rs.getString("telefono"));
		vivaio.setMail(rs.getString("mail"));
		vivaio.setPeriodoApertura(rs.getString("periodo_apertura"));
		vivaio.setOrarioApertura(rs.getString("orario_apertura"));
		vivaio.setComunicazioni(rs.getString("comunicazioni"));
		return vivaio;
	}

}
