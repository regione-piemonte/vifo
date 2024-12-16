/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PdfAttitudine;

public class PdfAttitudineMapper  implements RowMapper<PdfAttitudine> {

	@Override
	public PdfAttitudine mapRow(ResultSet rs, int arg1) throws SQLException {
		
		PdfAttitudine attitudine = new PdfAttitudine();
		
		attitudine.setNomeSpecie(rs.getString("nome_specie"));
		String specieLatino = rs.getString("specie_latino");
		attitudine.setNomeSpecie(attitudine.getNomeSpecie() + (specieLatino == null?"": " - " + specieLatino));
		
		attitudine.setAttitudini(rs.getString("attitudine"));
		if(attitudine.getAttitudini() == null) {
			attitudine.setAttitudini("-");
		}
		String hMin = rs.getString("min_h");
		String hMax = rs.getString("max_h");
		attitudine.setAltezzaMinMax(hMax ==  null? "-":hMin + " - " + hMax);
		
		return attitudine;		
	}

}
