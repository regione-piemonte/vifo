/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import it.csi.vifo.vifoapi.util.mail.MailEnum;

public interface MailService {
	String sendMail(int id, MailEnum tipoMail);
}
