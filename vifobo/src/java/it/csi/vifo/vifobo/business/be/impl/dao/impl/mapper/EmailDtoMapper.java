/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.util.mail.EmailDTO;

public class EmailDtoMapper implements RowMapper<EmailDTO>{

	@Override
	public EmailDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		EmailDTO email = new EmailDTO();
		email.setHost(rs.getString("host"));
		email.setPort(rs.getString("porta"));
		email.setUserID(rs.getString("user_id"));
		email.setPassword(rs.getString("psw"));
		email.setMittente(rs.getString("mittente"));
		email.setOggetto(rs.getString("oggetto"));
		email.setCorpo(rs.getString("testo"));
		return email;
	}

}
