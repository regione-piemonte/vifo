/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;


import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.MailDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.EmailDtoMapper;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.mail.EmailDTO;

@Component
public class MailDAOImpl extends GenericDAO implements MailDAO{

	@Override
	public EmailDTO getConfEmail(int idMail, Integer idconf) {
		StringBuilder sql = new StringBuilder();
		sql.append("select host, porta, user_id, psw, oggetto, testo, mittente ");
		sql.append("from vivai_cnf_mail a,vivai_cnf_tipo_mail b  ");
		sql.append("where b.id_tipo_mail = ? and a.id_mail = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new EmailDtoMapper(),idMail,idconf);	
	}

	@Override
	public EmailDTO getConfEmail(int idMail) {
		return getConfEmail(idMail,1);
	}	
	
}
