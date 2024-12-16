/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;


import it.csi.vifo.vifobo.util.mail.EmailDTO;

public interface MailDAO {
	EmailDTO getConfEmail(int idMail, Integer idconf);
	EmailDTO getConfEmail(int idMail);
}
