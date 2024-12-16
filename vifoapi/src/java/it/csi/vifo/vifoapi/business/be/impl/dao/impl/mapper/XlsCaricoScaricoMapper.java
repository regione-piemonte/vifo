/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.XlsCaricoScarico;
import it.csi.vifo.vifoapi.util.CalendarUtils;

public class XlsCaricoScaricoMapper implements RowMapper<XlsCaricoScarico> {

	@Override
	public XlsCaricoScarico mapRow(ResultSet rs, int arg1) throws SQLException {
		XlsCaricoScarico item = new XlsCaricoScarico();
		
		item.setIdInventario(rs.getInt("id_inventario_piante"));
		item.setDataMovimento(CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, rs.getDate("data_movimento")));
		item.setSpecie(rs.getString("specie"));
		String specieLatino = rs.getString("specie_latino");
		item.setSpecie(item.getSpecie() + (specieLatino == null?"": " - " + specieLatino));		
		
		item.setProvenienza(rs.getString("provenienza"));
		item.setCertificatoProvenienza(rs.getString("certificato_provenienza"));
		item.setQtaCarico(getInt(rs,"qta_carico"));
		item.setQtaScarico(getInt(rs,"qta_ritirata"));
		item.setIdDomanda(getInt(rs,"id_domanda"));
		item.setNumeroDdt(getString(rs,"numero_ddt"));
		item.setTipoDomanda(getString(rs,"tipo_domanda"));
		item.setRichiedente(rs.getString("richiedente"));
		
		return item;
	}
	
	private Integer getInt(ResultSet rs, String fieldName) throws SQLException{
		if(rs.getString(fieldName) != null) {
			return rs.getInt(fieldName);
		}
		return null;
	}
	
	private String getString(ResultSet rs, String fieldName) throws SQLException{
		if(rs.getString(fieldName) != null) {
			return rs.getString(fieldName);
		}
		return "";
	}


}
