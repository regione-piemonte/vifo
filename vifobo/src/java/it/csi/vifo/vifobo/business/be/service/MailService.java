/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service;

import it.csi.vifo.vifobo.util.mail.MailEnum;

public interface MailService {
	String sendMail(int id, MailEnum tipoMail);
}
