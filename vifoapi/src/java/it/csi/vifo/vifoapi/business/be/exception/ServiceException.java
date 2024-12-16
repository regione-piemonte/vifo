/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;


/**
 * Classe di eccezioni del <code>Servizio</code>.
 *
 * @author  1346 (Enrico Fusaro)
 */
public final class ServiceException extends Exception{

    private static final long serialVersionUID = 7583376730326946211L;

    public ServiceException(String message) {
		super(message);
	}

}
