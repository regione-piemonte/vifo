/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.ApiManagerDto;

public class ApiManagerDaoRowMapper  implements RowMapper<ApiManagerDto> {

	@Override
	public ApiManagerDto mapRow(ResultSet rs, int arg1) throws SQLException {
		ApiManagerDto item = new ApiManagerDto();
		item.setConsumerKey(rs.getString("consumer_key"));
		item.setConsumerSecret(rs.getString("consumer_secret"));
		item.setDataFineValidita(rs.getDate("data_fine_validita"));
		item.setDataInizioValidita(rs.getDate("data_inizio_validita"));
		item.setIdConfigParamApimgr(rs.getInt("id_config_param_apimgr"));
		item.setUrl(rs.getString("url"));
		return item;
	}

}
